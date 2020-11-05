package pe.edu.upc.delivery.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.delivery.models.entities.DetallePedido;
import pe.edu.upc.delivery.models.entities.Producto;
import pe.edu.upc.delivery.services.ProductoService;

@Controller
@RequestMapping("/productos")
@SessionAttributes("{producto, productoVenta, detallePedido}")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@PostMapping("search")
	public String search(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", producto);
		try {
			List<Producto> productos = productoService.findByDescripcion(producto.getDescripcion());
			model.addAttribute("productos", productos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productos/result-search";
	}
	
	@GetMapping("{tag}-{id}/p")
	public String view(@ModelAttribute("producto") Producto producto, @PathVariable("id") Integer id, Model model) {
		model.addAttribute("producto", producto);
		try {
			Optional<Producto> optional = productoService.findById(id);
			if(optional.isPresent()) {
				model.addAttribute("productoVenta", optional.get());
				
				DetallePedido detallePedido = new DetallePedido();
				detallePedido.setPrecio(optional.get().getPrecioVenta());
				detallePedido.setProducto(optional.get());
				detallePedido.setCantidad(1);
				
				model.addAttribute("detallePedido", detallePedido);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productos/view-producto";
	}
}






