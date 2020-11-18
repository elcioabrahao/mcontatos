package br.usjt.sisdist.meuscontatos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.sisdist.meuscontatos.model.Contato;
import br.usjt.sisdist.meuscontatos.repository.ContatoRepository;
import br.usjt.sisdist.meuscontatos.service.ContatoService;

@RestController
@RequestMapping("/api")
public class ContatoApiController {
	

	@Autowired
	ContatoService contatoService;
	
	
	  @GetMapping("/contatos")
	  public ResponseEntity<List<Contato>> getAllContatos() {
	    try {
	      List<Contato> contatos = new ArrayList<Contato>();


	      contatoService.findAll().forEach(contatos::add);


	      if (contatos.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(contatos, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	  @GetMapping("/contato/{id}")
	  public ResponseEntity<Contato> getContatoById(@PathVariable("id") long id) {
	    Optional<Contato> contatoData = contatoService.findById(id);

	    if (contatoData.isPresent()) {
	      return new ResponseEntity<>(contatoData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	  @PostMapping("/contato")
	  public ResponseEntity<Contato> createContato(@RequestBody Contato contato) {
	    try {
	      Contato _contato = contatoService
	          .save(new Contato(contato.getNome(), contato.getEmail(), contato.getTelefone(), contato.getImagem()));
	      return new ResponseEntity<>(_contato, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/contato/{id}")
	  public ResponseEntity<Contato> updateContato(@PathVariable("id") long id, @RequestBody Contato contato) {
	    Optional<Contato> contatoData = contatoService.findById(id);

	    if (contatoData.isPresent()) {
	      Contato _contato = contatoData.get();
	      _contato.setNome(contato.getNome());
	      _contato.setEmail(contato.getEmail());
	      _contato.setTelefone(contato.getTelefone());
	      _contato.setImagem(contato.getImagem());	      
	      return new ResponseEntity<>(contatoService.save(_contato), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @DeleteMapping("/contato/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      contatoService.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	

}
