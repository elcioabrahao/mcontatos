package br.usjt.sisdist.meuscontatos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoWebController {
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@GetMapping ("/webcontatos")
	public ModelAndView listarContatos () {
		//passe o nome da página ao construtor
		ModelAndView mv = new ModelAndView ("lista_contatos");
			
		//Busque a coleção com o repositório
		List <Contato> contatos = contatoRepository.findAll();
			
		//adicione a coleção ao objeto ModelAndView		
		mv.addObject("contatos", contatos);
			
		//devolva o ModelAndView
		return mv;
	}


}
