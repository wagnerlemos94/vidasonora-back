package com.vidasonora.lemos.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		assertNotNull(usuariosalvo);
	}
	
	@Test
	void buscarUsuarioPorId() {
		this.savarUsuario();
		Usuario user = service.buscarPorId(Long.valueOf(1));
		assertEquals(user.getId(), 1);
	}
	
	@Test
	void erroAoBuscarUsuarioPorId() {
		long id = 1;
		repository.deleteById(id);
		ObjetoNaoEncontrado obj = assertThrows(ObjetoNaoEncontrado.class, () -> service.buscarPorId(Long.valueOf(id)));
		assertEquals("Usuário não encontrado id: " + id, obj.getMessage());
	}
	
	private Usuario savarUsuario() {
		return service.cadastro(usuario);
	}
	
}
