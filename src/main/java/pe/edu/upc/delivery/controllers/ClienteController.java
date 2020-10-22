package pe.edu.upc.delivery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping
	public String menu() {
		return "clientes/NewFile";
	}
	@GetMapping("search")
	public String search() {
		return "clientes/search";
	}
}
