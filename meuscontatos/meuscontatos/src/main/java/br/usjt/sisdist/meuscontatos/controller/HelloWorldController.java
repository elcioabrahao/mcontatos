package br.usjt.sisdist.meuscontatos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
	
	@GetMapping("/hello")
	public String getHello() {
		
		return "meu_hello_world";
	}

}
