package com.joaopedro.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaopedro.br.model.Pessoa;
import com.joaopedro.br.persistence.PersistencePessoa;
import com.joaopedro.br.persistence.filtro.PessoaFilter;

@Service
public class CadastroPessoaService {

	@Autowired
	private PersistencePessoa persistencePessoa;

	public void salvar(Pessoa pessoa) {
		persistencePessoa.save(pessoa);
	}

	public void excluir(Long id) {
		persistencePessoa.delete(id);
	}
	
	public List<Pessoa> filtrar(PessoaFilter filtro){
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return persistencePessoa.findByNomeContaining(nome);
		
	}
}

