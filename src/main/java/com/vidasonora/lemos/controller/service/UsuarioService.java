package com.vidasonora.lemos.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario cadastro(Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		
		return usuario;
	}
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return usuario.orElseThrow(() -> new ObjetoNaoEncontrado("Usuarário não encontrado Id: " + id));
	}
	
	public List<Usuario> buscarTodos(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarios;
	}
	
	@Transactional
	public Usuario editar(Usuario usuario) {
		try {
			buscarPorId(usuario.getId());
			usuario = usuarioRepository.save(usuario);
			return usuario;
		} catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoAtualizado(e.getMessage()); 	
		}
	}
	
	@Transactional
	public String delete(Long id) {
		try {
			Usuario usuario = buscarPorId(id);
			usuario.setStatus(0);
			usuarioRepository.save(usuario);
			return "Usuário deletado com Sucesso!";
		} catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoDeletado(e.getMessage());
		}
	}
	
}
