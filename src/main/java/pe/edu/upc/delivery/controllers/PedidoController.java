package pe.edu.upc.delivery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.delivery.models.entities.DetallePedido;

@Controller
@RequestMapping("/pedidos")
@SessionAttributes("{producto, detallePedido}")
public class PedidoController {
	
	@PostMapping("adddetail")
	public String addDetail(@ModelAttribute("detallePedido") DetallePedido detallePedido,
			Model model) {
		//System.out.println(detallePedido.getProducto().getDescripcion());
		System.out.println(detallePedido.getCantidad());
		//System.out.println(detallePedido.getProveedor().getNombre());
		return "redirect:/";
	}
}
