package com.vidasonora.lemos.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa cadastro(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa = pessoaRepository.save(pessoa);		
		return pessoa;
	}
	
	public Pessoa buscarPorId(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		return pessoa.orElseThrow(() -> new ObjetoNaoEncontrado("Pessoa não encontrado id: " + id));
	}
	
	public List<Pessoa> buscarTodos(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}
	
	public Pessoa editar(Pessoa pessoa) {
		try {			
			buscarPorId(pessoa.getId());
			pessoa = pessoaRepository.save(pessoa);		
		}catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoAtualizado("Pessoa não encontrada para Editar");
		}
		return pessoa;
	}
	
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}

}
