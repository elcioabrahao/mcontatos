package br.usjt.sisdist.meuscontatos.service;

import java.util.List;
import java.util.Optional;


import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.usjt.sisdist.meuscontatos.model.Usuario;
import br.usjt.sisdist.meuscontatos.repository.UsuarioRepository;

import static java.util.Collections.emptyList;

@Service
public class UsuarioServiceImpl implements UserDetailsService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> opuser = usuarioRepository.findByEmail(username);
		
		
        if (!opuser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
		System.out.println("Usuario-->"+opuser.get().toString());

        return new User(opuser.get().getEmail(), opuser.get().getSenha(), emptyList());
	}


}
