package com.joaopedro.br.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 60, message = "O nome não pode possuir mais que 60 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preencha o sobrenome")
	private String sobrenome;
	
	@NotEmpty(message = "Informe o telefone")
	@Size(max = 15, message = "Número de telefone não pode possuir mais que 15 números")
	private String telefone;
	
	
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
}
