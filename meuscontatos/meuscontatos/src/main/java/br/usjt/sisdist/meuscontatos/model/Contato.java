package br.usjt.sisdist.meuscontatos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contato implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	@Column(length = 500000)
	private String imagem;
	private Long remoto;
	
	
	
	public Contato(String nome, String email, String telefone, String imagem, Long remoto) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.imagem = imagem;
		this.remoto = remoto;
	}
	
	
	
	public Contato() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
	public Long getRemoto() {
		return remoto;
	}



	public void setRemoto(Long remoto) {
		this.remoto = remoto;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
