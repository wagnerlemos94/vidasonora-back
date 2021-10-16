package com.vidasonora.lemos.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.vidasonora.lemos.controller.service.PessoaService;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Pessoa;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pessoa> cadastro(@RequestBody Pessoa pessoa){
		pessoa =  pessoaService.cadastro(pessoa);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscar(@PathVariable Long id){
		
		try {			
			Pessoa pessoa = pessoaService.buscarPorId(id);
			return ResponseEntity.ok().body(pessoa);
		} catch (ObjetoNaoEncontrado e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> buscarTodos(){
		List<Pessoa> pessoas = pessoaService.buscarTodos();
		return ResponseEntity.ok().body(pessoas);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> editar(@PathVariable Long id, @RequestBody Pessoa pessoa){
		try {			
			Pessoa pessoaEditado = pessoaService.editar(pessoa);
			return ResponseEntity.ok().body(pessoaEditado);
		} catch (ObjetoNaoAtualizado e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
