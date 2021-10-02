package com.vidasonora.lemos.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Pessoa> cadastro(@RequestBody Pessoa paciente){
		paciente =  pessoaService.cadastro(paciente);
		return ResponseEntity.ok().body(paciente);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Long id){
		
		try {			
			Pessoa paciente = pessoaService.buscarPorId(id);
			return ResponseEntity.ok().body(paciente);
		} catch (ObjetoNaoEncontrado e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> buscarTodos(){
		List<Pessoa> pacientes = pessoaService.buscarTodos();
		return ResponseEntity.ok().body(pacientes);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> editar(@RequestBody Pessoa paciente){
		try {			
			Pessoa pacienteEditado = pessoaService.editar(paciente);
			return ResponseEntity.ok().body(pacienteEditado);
		} catch (ObjetoNaoAtualizado e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
