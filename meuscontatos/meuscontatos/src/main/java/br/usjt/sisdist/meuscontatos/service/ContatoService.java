package br.usjt.sisdist.meuscontatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.sisdist.meuscontatos.model.Contato;
import br.usjt.sisdist.meuscontatos.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository contatoRepository;
	
	public List<Contato> findAllByRemote(Long id){
		return contatoRepository.findAllByRemoto(id);
	}

	public Optional<Contato> findById(Long id) {
		return contatoRepository.findById(id);
	}
	
	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}
	
	public void deleteById(Long id) {
		contatoRepository.deleteById(id);
	}
	
}
