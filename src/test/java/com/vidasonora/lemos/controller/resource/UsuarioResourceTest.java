package com.vidasonora.lemos.controller.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vidasonora.lemos.controller.service.UsuarioService;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioResourceTest {

	@Autowired
	private UsuarioResource resource;
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioService service;
	
	private final Usuario usuario = new Usuario(null,"testes", "teste@teste.com.br", "123123", 1, null);
	
	@Test
	public void CadastrarUmUsuario() {
		repository.deleteAll();
		ResponseEntity<Usuario> user = resource.cadastro(usuario);
		assertEquals(HttpStatus.OK, user.getStatusCode());
		assertNotEquals(null, user.getBody().getId());
		
	}
	
	@Test
	public void buscarUsuarioPorId() {
		repository.deleteAll();
		long id = service.cadastro(usuario).getId();
		ResponseEntity<Usuario> user = resource.buscar(id);
		assertEquals(HttpStatus.OK, user.getStatusCode());
		assertNotEquals(null, user.getBody().getId());
		
	}
	
	@Test
	public void erroAoBuscarUsuarioPorId() {
		repository.deleteAll();
		long id = 1;
		ObjetoNaoEncontrado obj = assertThrows(ObjetoNaoEncontrado.class, () -> resource.buscar(Long.valueOf(id)));
		assertEquals("Usuário não encontrado Id: " + id, obj.getMessage());
	}
	
	@Test
	public void deletarUmUsuarioPorId() {
		Long id = service.cadastro(usuario).getId();
		resource.delete(id);
		int StatusInativo = 2;  
		int status = repository.findById(id).get().getStatus();
		assertEquals(StatusInativo, status);
	}
	
	@Test
	public void autenticarUsuario() {
		repository.deleteAll();
		Usuario user = service.cadastro(usuario);
		user.setSenha("123123");
		HttpStatus statusCode = resource.autenticar(usuario).getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}
	
	@Test
	public void erroAutenticarUsuario() {
		repository.deleteAll();
		Usuario user = service.cadastro(usuario);
		user.setSenha("32131");
		HttpStatus statusCode = resource.autenticar(usuario).getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, statusCode);
	}
	
}
