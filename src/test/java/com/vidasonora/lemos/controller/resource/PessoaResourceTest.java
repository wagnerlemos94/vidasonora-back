package com.vidasonora.lemos.controller.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vidasonora.lemos.model.entity.Cidade;
import com.vidasonora.lemos.model.entity.Contato;
import com.vidasonora.lemos.model.entity.Endereco;
import com.vidasonora.lemos.model.entity.Estado;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.repository.EstadoRepository;
import com.vidasonora.lemos.model.repository.PessoaRepository;

@SpringBootTest
public class PessoaResourceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
	@Autowired
	private PessoaRepository repository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private PessoaResource resource;

	private Pessoa montarUmaPessoa(){
		try {			
			repository.deleteAll();
			estadoRepository.deleteAll();
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
			
			return pessoa;
		} catch (Exception e) {
			return new Pessoa();
		}
	}
	
	@Test
	public void cadastrarUmaPessoa() {
		Pessoa pessoaNova = this.montarUmaPessoa();
		ResponseEntity<Pessoa> pessoaSalva = resource.cadastro(pessoaNova);
		assertEquals(HttpStatus.OK, pessoaSalva.getStatusCode());
		assertNotEquals(null, pessoaSalva.getBody().getId());
	}
	
	@Test
	public void buscarUmaPessoaPorId() {
		Pessoa pessoaNova = this.montarUmaPessoa();
		pessoaNova = resource.cadastro(pessoaNova).getBody();
		ResponseEntity<Pessoa> pessoaBuscada = resource.buscar(pessoaNova.getId());
		assertEquals(HttpStatus.OK, pessoaBuscada.getStatusCode());
		assertNotNull(pessoaBuscada.getBody());
	}
	
	@Test
	public void deletarUmUsuarioPorId() {
		int statusInativo = 2;
		Pessoa pessoaNova = this.montarUmaPessoa();
		pessoaNova = resource.cadastro(pessoaNova).getBody();
		resource.delete(pessoaNova.getId());
		pessoaNova = resource.buscar(pessoaNova.getId()).getBody();
		assertEquals(statusInativo, pessoaNova.getStatus());
	}
	
}
