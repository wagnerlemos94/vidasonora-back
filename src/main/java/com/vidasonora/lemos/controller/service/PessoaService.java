package com.vidasonora.lemos.controller.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.repository.ContatoRepository;
import com.vidasonora.lemos.model.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	ContatoRepository contatoRepository;
	
	@Transactional
	public Pessoa cadastro(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa.setStatus(1);
		pessoa.getContatos().forEach(contato -> contato.setPessoa(pessoa));
		pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
		return pessoaRepository.save(pessoa);	
	}
	
	public Pessoa buscarPorId(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		return pessoa.orElseThrow(() -> new ObjetoNaoEncontrado("Pessoa não encontrado id: " + id));
	}
	
	public List<Pessoa> buscarTodos(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}
	
	@Transactional
	public Pessoa editar(Long id, Pessoa pessoa) {
		try {			
			buscarPorId(id);
			pessoa.getContatos().forEach(contato -> contato.setPessoa(pessoa));
			pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
			return pessoaRepository.save(pessoa);
		}catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoAtualizado("Pessoa não encontrada para Editar");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		Pessoa pessoa = buscarPorId(id);
		pessoa.setStatus(2);
		pessoaRepository.save(pessoa);		
	}

}
