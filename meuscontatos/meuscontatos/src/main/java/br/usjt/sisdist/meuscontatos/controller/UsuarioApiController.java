package br.usjt.sisdist.meuscontatos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.sisdist.meuscontatos.model.Usuario;
import br.usjt.sisdist.meuscontatos.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioApiController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	  @GetMapping("/usuarios")
	  public ResponseEntity<List<Usuario>> getAllUsuarios() {
	    try {
	      List<Usuario> usuarios = new ArrayList<Usuario>();


	      for(Usuario us:  usuarioService.findAll()) {
	    	  us.setSenha("");
	    	  usuarios.add(us);
	      }


	      if (usuarios.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(usuarios, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	  @GetMapping("/usuario/{id}")
	  public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long id) {
	    Optional<Usuario> UsuarioData = usuarioService.findById(id);

	    if (UsuarioData.isPresent()) {
	      return new ResponseEntity<>(UsuarioData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @PostMapping("/usuario/autenticar")
	  public ResponseEntity<Usuario> getUsuario(@RequestBody Usuario usuario) {
		  
	    Optional<Usuario> UsuarioData = usuarioService.findByEmail(usuario.getEmail());
	    
//	    System.out.println("Senha da chamada   :"+usuario.getSenha());
//	    System.out.println("Senha da encriptada:"+bCryptPasswordEncoder.encode(usuario.getSenha()));
//	    System.out.println("Senha do banco     :"+UsuarioData.get().getSenha());

	    if (UsuarioData.isPresent()) {
	    	
	    	Usuario us = UsuarioData.get();
	    	us.setSenha(usuario.getSenha());
	    	
   		return new ResponseEntity<>(us, HttpStatus.OK);

	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	  @PostMapping("/usuario/novo")
	  public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
	    try {
	      Usuario _Usuario = usuarioService
	          .save(new Usuario(usuario.getNome(), usuario.getCpf(), usuario.getEmail(), bCryptPasswordEncoder.encode(usuario.getSenha())));
	      usuario.setId(_Usuario.getId());
	      return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  // TODO ajustar o ID local do webservice com o ID do app
	  @PutMapping("/usuario/{id}")
	  public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario Usuario) {
	    Optional<Usuario> UsuarioData = usuarioService.findById(id);

	    if (UsuarioData.isPresent()) {
	      Usuario _Usuario = UsuarioData.get();
	      _Usuario.setNome(Usuario.getNome());
	      _Usuario.setCpf(Usuario.getCpf());
	      _Usuario.setEmail(Usuario.getEmail());
	      _Usuario.setSenha(Usuario.getSenha());	      
	      return new ResponseEntity<>(usuarioService.save(_Usuario), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  // TODO ajustar o ID local do webservice com o ID do app
	  @DeleteMapping("/usuario/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      usuarioService.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	

}
