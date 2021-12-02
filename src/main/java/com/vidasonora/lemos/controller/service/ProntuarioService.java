package com.vidasonora.lemos.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.entity.Prontuario;
import com.vidasonora.lemos.model.repository.ProntuarioRepository;

@Service
public class ProntuarioService {
	
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	
	
	public void cadastro(Prontuario prontuario) {
		prontuario.setId(null);
		prontuarioRepository.save(prontuario);
	}
	
	public Prontuario buscarPorId(Long id) {
		Optional<Prontuario> prontuario = prontuarioRepository.findById(id);
		return prontuario.orElseThrow(() -> new ObjetoNaoEncontrado("Prontuario não encontrado Id: " + id));
	}
	
	public List<Prontuario> buscarTodos(){
		List<Prontuario> prontuarios = prontuarioRepository.findAll();
		return prontuarios;
	}

}
