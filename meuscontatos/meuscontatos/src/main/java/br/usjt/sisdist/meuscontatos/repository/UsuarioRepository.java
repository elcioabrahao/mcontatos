package br.usjt.sisdist.meuscontatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.sisdist.meuscontatos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
