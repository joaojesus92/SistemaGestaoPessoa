package com.joaopedro.br.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaopedro.br.model.Pessoa;

public interface PersistencePessoa extends JpaRepository<Pessoa, Long>{

//Pesquise pelo "Nome" que contêm o valor passado por parâmetro! 
	public List<Pessoa> findByNomeContaining(String nome);
}
