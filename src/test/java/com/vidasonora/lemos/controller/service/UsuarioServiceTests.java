package com.vidasonora.lemos.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vidasonora.lemos.controller.service.exception.AutenticacaoException;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTests {
	
	@Autowired
	private UsuarioService service;
	@Autowired
	private UsuarioRepository repository;
	
	private final Usuario usuario = new Usuario(null, "teste@teste.com.br", "123123", 1);
	
	@Test
	 void salvaUsuarioNoBancoDeDados() {
		Usuario usuariosalvo = service.cadastro(usuario);
		assertNotNull(usuariosalvo.getId());
	}
	
	@Test
	void buscarUsuarioPorId() {
		Long id = this.savarUsuario().getId();
		Usuario user = service.buscarPorId(Long.valueOf(id));
		assertEquals(id, user.getId());
	}
	
	@Test
	void erroAoBuscarUsuarioPorId() {
		repository.deleteAll();
		long id = 1;
		ObjetoNaoEncontrado obj = assertThrows(ObjetoNaoEncontrado.class, () -> service.buscarPorId(Long.valueOf(id)));
		assertEquals("Usuário não encontrado Id: " + id, obj.getMessage());
	}
	
	@Test
	void inativarUmUsuario() {
		int inativo = 2;
		Usuario user = this.savarUsuario();
		service.delete(user.getId());
		user = service.buscarPorId(user.getId());
		assertEquals(inativo,user.getStatus());
	}
	
	@Test
	void autenticarUsuario() {
		Usuario user = this.savarUsuario();
		user.setSenha("123123");
		service.auntenticar(user);
		assertNotNull(user);
		
	}
	
	@Test
	void erroNaAutenticacaoDoUsuario() {
		this.savarUsuario();
		Usuario user = new Usuario(null, "admi@admin.com", "123123", null);
		AutenticacaoException obj = assertThrows(AutenticacaoException.class,() -> service.auntenticar(user));
		assertEquals("Usuário ou senha Inválidos", obj.getMessage());
	}
	
	private Usuario savarUsuario() {
		repository.deleteAll();
		return service.cadastro(usuario);
	}
	
}
