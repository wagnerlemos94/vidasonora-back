package com.vidasonora.lemos.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vidasonora.lemos.controller.service.exception.AutenticacaoException;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoAtualizado;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Transactional
	public Usuario cadastro(Usuario usuario) {
		usuario.setId(null);
		usuario.setStatus(1);
		usuario.setSenha(pe.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);	
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
			usuarioRepository.flush();
			return "Usuário deletado com Sucesso!";
		} catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoDeletado(e.getMessage());
		}
	}
	
	public Usuario auntenticar(Usuario usuario) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(!usuarioExistente.isPresent() || !pe.matches(usuario.getSenha(), usuarioExistente.get().getSenha())) {
			throw new AutenticacaoException("Usuário ou senha Inválidos");
		}
		return usuarioExistente.get();
	}

}
