package com.vidasonora.lemos.controller.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Endereco;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.entity.Prontuario;
import com.vidasonora.lemos.model.repository.CidadeRepository;
import com.vidasonora.lemos.model.repository.ContatoRepository;
import com.vidasonora.lemos.model.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ProntuarioService prontuarioService;
	@Autowired
	private ContatoRepository contatoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	
	@Transactional
	public Pessoa cadastro(Pessoa pessoa) {
		Pessoa pessoaSalva;
		pessoa.setId(null);
		pessoa.setStatus(1);
		relacionaEnderecos(pessoa);
		pessoa.getContatos().forEach(contato -> contato.setPessoa(pessoa));
		pessoaSalva = pessoaRepository.save(pessoa);
		criarProntuario(pessoa);
		return pessoaSalva;	
	}
	
	private void relacionaEnderecos(Pessoa pessoa) {
		for(Endereco endereco : pessoa.getEnderecos()) {
			endereco.setCidade(cidadeRepository.findByNome(endereco.getCidade().getNome()));
			endereco.setPessoa(pessoa);
		}
	}
	
	private void criarProntuario(Pessoa pessoa) {
		Prontuario prontuario = new Prontuario();
		prontuario.setPessoa(pessoa);
		prontuarioService.cadastro(prontuario);
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
			contatoRepository.deleteByPessoa(pessoa);
			pessoa.getContatos().forEach(contato -> contato.setPessoa(pessoa));
			relacionaEnderecos(pessoa);
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
