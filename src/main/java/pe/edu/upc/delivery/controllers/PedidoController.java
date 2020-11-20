package pe.edu.upc.delivery.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.delivery.models.entities.Cliente;
import pe.edu.upc.delivery.models.entities.DetallePedido;
import pe.edu.upc.delivery.models.entities.Pedido;
import pe.edu.upc.delivery.models.entities.Producto;
import pe.edu.upc.delivery.security.UsuarioDetails;
import pe.edu.upc.delivery.services.ClienteService;
import pe.edu.upc.delivery.services.PedidoService;

@Controller
@RequestMapping("/pedidos")
@SessionAttributes("{producto, detallePedido}")
public class PedidoController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("adddetail")
	public String addDetail(@ModelAttribute("detallePedido") DetallePedido detallePedido,
			Model model) {
		//System.out.println(detallePedido.getProducto().getDescripcion());
		System.out.println(detallePedido.getCantidad());
		//System.out.println(detallePedido.getProveedor().getNombre());
		return "redirect:/";
	}
	
	@GetMapping("mis")
	public String misPedidos(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", producto);
		// Obtener el cliente
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			Optional<Cliente> optional = clienteService.findById(usuarioDetails.getIdSegmento());
			model.addAttribute("cliente", optional.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "pedidos/view-pedidos";
	}
	
	@GetMapping("{id}/d")
	public String verDetalle(@ModelAttribute("producto") Producto producto, Model model,
			@PathVariable("id") Integer id) {
		model.addAttribute("producto", producto);
		
		try {
			Optional<Pedido> optional = pedidoService.findById(id);
			model.addAttribute("pedido", optional.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pedidos/view-detalles";
	}
}
