package com.vidasonora.lemos.controller.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioResourceTest {

	@Autowired
	private UsuarioResource resource;
	@Autowired
	private UsuarioRepository repository;
	
	private final Usuario usuario = new Usuario(null, "teste@teste.com.br", "123123", 1);
	
	@Test
	public void CadastrarUmUsuario() {
		repository.deleteAll();
		ResponseEntity<Usuario> user = resource.cadastro(usuario);
		assertNotEquals(null, user.getBody().getId());
		
	}
}
