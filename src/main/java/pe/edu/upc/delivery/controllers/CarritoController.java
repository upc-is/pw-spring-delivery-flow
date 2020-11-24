package pe.edu.upc.delivery.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.delivery.models.entities.Cliente;
import pe.edu.upc.delivery.models.entities.DetallePedido;
import pe.edu.upc.delivery.models.entities.Pedido;
import pe.edu.upc.delivery.models.entities.Producto;
import pe.edu.upc.delivery.security.UsuarioDetails;
import pe.edu.upc.delivery.services.DetallePedidoService;
import pe.edu.upc.delivery.services.PedidoService;
import pe.edu.upc.delivery.utils.EstadoPedido;

@Controller
@RequestMapping("/carritos")
@SessionAttributes("{producto, detallePedido}")
public class CarritoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallePedidoService detallePedidoService;
	
	@GetMapping("mis")
	public String misCarritos(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", producto);
		// Obtener el cliente y/o Proveedor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		try {
			List<Pedido> pedidos = pedidoService.findByClienteAndEstadoPedido(
					usuarioDetails.getIdSegmento(), EstadoPedido.CARRITO);
		
			model.addAttribute("pedidos", pedidos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "carritos/view-carritos";
	}
	
	@GetMapping("{id}/d")
	public String BuyCarrito(@ModelAttribute("producto") Producto producto, Model model,
			@PathVariable("id") Integer id) {
		model.addAttribute("producto", producto);
		
		try {
			Optional<Pedido> optional = pedidoService.findById(id);
			model.addAttribute("pedido", optional.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "carritos/view-detalles";
	}
	@GetMapping("{id}/buy")
	public String verDetalle(@ModelAttribute("producto") Producto producto, Model model,
			@PathVariable("id") Integer id) {
		model.addAttribute("producto", producto);
		
		try {
			Optional<Pedido> optional = pedidoService.findById(id);
			// Cambiando el estado del pedido
			optional.get().setEstadoPedido(EstadoPedido.PAGADO);
			optional.get().setFechaPedido(Date.valueOf("2020-11-24"));
			pedidoService.save(optional.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/carritos/mis";
	}
	
	@GetMapping("{id}/edp")
	public String delDetallePedido(@ModelAttribute("producto") Producto producto, Model model,
			@PathVariable("id") Integer id) {
		model.addAttribute("producto", producto);
		
		try {
			Optional<DetallePedido> optional = detallePedidoService.findById(id);
			if(optional.isPresent()) {
				detallePedidoService.deleteById(id);
				return "redirect:/carritos/" + optional.get().getPedido().getId() + "/d";
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/carritos/mis";
	}

}
