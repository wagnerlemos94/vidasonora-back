package com.vidasonora.lemos.controller.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.entity.Prontuario;
import com.vidasonora.lemos.model.repository.AnamneseRepository;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository anamneseRepository;
	@Autowired
	private ProntuarioService prontuarioService;
	
	@Transactional
	public Anamnese cadastro(Anamnese anamnese, Long idPessoa) {
		anamnese.setId(null);
		anamnese = anamneseRepository.save(anamnese);
		anamnese.relacionaEntidades(anamnese);
		Prontuario prontuario = prontuarioService.buscarPorId(idPessoa);
		prontuario.getAnamneses().add(anamnese);
		return anamnese;
	}

	
}
