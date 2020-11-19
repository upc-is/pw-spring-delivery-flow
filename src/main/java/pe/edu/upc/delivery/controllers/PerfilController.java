package pe.edu.upc.delivery.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.delivery.models.entities.Cliente;
import pe.edu.upc.delivery.models.entities.Producto;
import pe.edu.upc.delivery.models.entities.Proveedor;
import pe.edu.upc.delivery.models.entities.Usuario;
import pe.edu.upc.delivery.security.UsuarioDetails;
import pe.edu.upc.delivery.services.ClienteService;
import pe.edu.upc.delivery.services.ProveedorService;
import pe.edu.upc.delivery.utils.Segmento;

@Controller
@RequestMapping("/perfil")
@SessionAttributes("{producto}")
public class PerfilController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	public String perfil(@ModelAttribute("producto") Producto producto, Model model) {
		// Obtener el usuarioDetails para obtener los datos de CLiente o proveedor
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails usuarioDetails = (UsuarioDetails)authentication.getPrincipal();
		
		// Obtener los datos del CLiente o proveedor 
		try {
			if(usuarioDetails.getSegmento() == Segmento.CLIENTE) {
				Optional<Cliente> optional = clienteService.findById(usuarioDetails.getIdSegmento());
				if (optional.isPresent()) {
					model.addAttribute("segmento", "CLIENTE");
					model.addAttribute("cliente", optional.get());
				}
			} 
			else if(usuarioDetails.getSegmento() == Segmento.PROVEEDOR) {
				Optional<Proveedor> optional = proveedorService.findById(usuarioDetails.getIdSegmento());
				if (optional.isPresent()) {
					model.addAttribute("segmento", "PROVEEDOR");
					model.addAttribute("proveedor", optional.get());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		model.addAttribute("producto", producto);
		return "perfil/perfil";
	}
}
