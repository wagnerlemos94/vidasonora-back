package com.vidasonora.lemos.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidasonora.lemos.controller.service.UsuarioService;
import com.vidasonora.lemos.controller.service.exception.AutenticacaoException;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Usuario;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastro(@RequestBody Usuario usuario){
		usuario =  usuarioService.cadastro(usuario);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Long id){
		
		try {			
			Usuario usuario = usuarioService.buscarPorId(id);
			return ResponseEntity.ok().body(usuario);
		} catch (ObjetoNaoEncontrado e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarTodos(){
		List<Usuario> usuarios = usuarioService.buscarTodos();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Usuario usuario){
		try {			
			Usuario usuarioEditado = usuarioService.editar(id, usuario);
			return ResponseEntity.ok().body(usuarioEditado);
		} catch (ObjetoNaoAtualizado e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		usuarioService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public ResponseEntity<?> autenticar(@RequestBody Usuario usuario){
		try {
			usuario = usuarioService.auntenticar(usuario);
			return ResponseEntity.ok().body(usuario);
		} catch (AutenticacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
