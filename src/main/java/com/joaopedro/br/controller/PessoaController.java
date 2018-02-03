package com.joaopedro.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joaopedro.br.model.Pessoa;
import com.joaopedro.br.persistence.PersistencePessoa;
import com.joaopedro.br.persistence.filtro.PessoaFilter;
import com.joaopedro.br.service.CadastroPessoaService;

@RequestMapping("/gestaopessoa")
@Controller
public class PessoaController {

	private static final String CADASTRO_PESSOA = "CadastroPessoa";
	
	@Autowired
	private PersistencePessoa persistence;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView(CADASTRO_PESSOA);
		mv.addObject(new Pessoa());
		return mv;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Pessoa pessoa, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_PESSOA;
		}

//Salva na banco se não tiver "id" populado, mas se for um objeto de retorno da edição o hibernate entende que queremos realizar uma edição!
		cadastroPessoaService.salvar(pessoa);
		
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso");
		return "redirect:/gestaopessoa/home";
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") PessoaFilter filtro) {
		List<Pessoa> todasPessoas = cadastroPessoaService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("Pesquisa");
		mv.addObject("pessoas", todasPessoas);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		System.out.println("id recebido: " + id);
		Pessoa pessoa = persistence.findOne(id);
		ModelAndView mv = new ModelAndView(CADASTRO_PESSOA);
		mv.addObject(pessoa);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		cadastroPessoaService.excluir(id);
		
		attributes.addFlashAttribute("mensagem", "Pessoa excluida com sucesso");
		return "redirect:/gestaopessoa";
	}
}
