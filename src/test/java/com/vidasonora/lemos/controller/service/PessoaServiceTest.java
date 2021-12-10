package com.vidasonora.lemos.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Cidade;
import com.vidasonora.lemos.model.entity.Contato;
import com.vidasonora.lemos.model.entity.Endereco;
import com.vidasonora.lemos.model.entity.Estado;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.repository.EstadoRepository;
import com.vidasonora.lemos.model.repository.PessoaRepository;

@SpringBootTest
public class PessoaServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
	@Autowired
	private PessoaRepository repository;
	@Autowired
	private PessoaService service;
	@Autowired
	private EstadoRepository estadoRepository;

	private Pessoa criarUmaPessoaEmCascade(){
		try {			
			repository.deleteAll();
			Cidade cidade = new Cidade(null, "Salvador");
			
			List<Cidade> cidades = new ArrayList<Cidade>();
			cidades.addAll(Arrays.asList(cidade));
			
			Estado estado = new Estado(null, "BAHIA", "BA", cidades);
			cidade.setEstado(estado);
			estadoRepository.save(estado);
			
			Pessoa pessoa = new Pessoa(null, "Teste", "010203040506", "41526520", "testador", sdf.parse("01/02/2020"));
			
			Contato contato1 = new Contato(null, "CELULAR","7113246579", pessoa);
			Contato contato2 = new Contato(null, "EMAIL","contato@contato.com", pessoa);
			
			Endereco endereco1 = new Endereco(null, "41250000", "Qualquer", "de cima", "0000", "Sem complemento", pessoa, cidade);
			
			pessoa.setContatos(Arrays.asList(contato1,contato2));
			pessoa.setEnderecos(Arrays.asList(endereco1));
			service.cadastro(pessoa);
			return pessoa;
		} catch (Exception e) {
			return new Pessoa();
		}
	}
	
	  
	@Test
	public void salvaUmaPessoaNoBancoDeDados(){
		Pessoa pessoa = this.criarUmaPessoaEmCascade();
		assertNotEquals(null, pessoa.getId());		
	}
	
	@Test
	public void buscarUmaPessoaPorId(){
		Long id = this.criarUmaPessoaEmCascade().getId();
		Pessoa pessoaSalva = service.buscarPorId(id);
		assertEquals(id, pessoaSalva.getId());
	}

	@Test
	public void erroAoBuscarPessoaPorID() {
		repository.deleteAll();
		long id = 1;
		ObjetoNaoEncontrado obj = assertThrows(ObjetoNaoEncontrado.class, () -> service.buscarPorId(Long.valueOf(id)));
		assertEquals("Pessoa não encontrado id: " + id, obj.getMessage());
	}
	
	@Test
	public void inativarUmaPessoa(){
		int inativo = 2;
		long id = this.criarUmaPessoaEmCascade().getId();
		service.delete(id);
		Pessoa pessoaInativada = service.buscarPorId(id);
		assertEquals(inativo, pessoaInativada.getStatus());
	}
	
}
