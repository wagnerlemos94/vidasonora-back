package com.vidasonora.lemos.controller.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.dto.prontuario.ProntuarioConsultaDTO;
import com.vidasonora.lemos.model.entity.Prontuario;
import com.vidasonora.lemos.model.repository.ProntuarioRepository;

@Service
public class ProntuarioService {
	
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	
	@Transactional
	public void cadastro(Prontuario prontuario) {
		prontuario.setId(null);
		prontuarioRepository.save(prontuario);
	}
	
	public ProntuarioConsultaDTO buscarPorId(Long id) {
		try {
			Prontuario prontuario = prontuarioRepository.findById(id).get();
			ProntuarioConsultaDTO prontuarioConsultaDTO = new ProntuarioConsultaDTO();
			prontuarioConsultaDTO = prontuarioConsultaDTO.convertToProntuario(prontuario);
			return prontuarioConsultaDTO;
		} catch (Exception e) {
			throw new ObjetoNaoEncontrado("Prontuario não encontrado Id: " + id);
		}
	}
	
	public List<Prontuario> buscarTodos(){
		List<Prontuario> prontuarios = prontuarioRepository.findAll();
		return prontuarios;
	}

}
