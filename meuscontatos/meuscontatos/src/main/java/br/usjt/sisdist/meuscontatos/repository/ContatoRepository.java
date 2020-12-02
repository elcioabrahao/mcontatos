package br.usjt.sisdist.meuscontatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.sisdist.meuscontatos.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	public List<Contato> findAllByRemoto(Long remote);

}
