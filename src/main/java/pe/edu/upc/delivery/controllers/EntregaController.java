package pe.edu.upc.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.delivery.models.entities.DetallePedido;
import pe.edu.upc.delivery.models.entities.Pedido;
import pe.edu.upc.delivery.models.entities.Producto;
import pe.edu.upc.delivery.security.UsuarioDetails;
import pe.edu.upc.delivery.services.DetallePedidoService;
import pe.edu.upc.delivery.utils.EstadoPedido;

@Controller
@RequestMapping("/entregas")
@SessionAttributes("{producto, detallePedido}")
public class EntregaController {
	
	@Autowired
	private DetallePedidoService detallePedidoService;
	
	@GetMapping("mis")
	public String misEntregas(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", producto);
		// Obtener el cliente y/o Proveedor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			List<DetallePedido> detallePedidos = detallePedidoService
					.findByProveedorAndNotEstadoPedido(
					usuarioDetails.getIdSegmento(), EstadoPedido.CARRITO);
		
			model.addAttribute("detallePedidos", detallePedidos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "entregas/view-entregas";
	}
}
