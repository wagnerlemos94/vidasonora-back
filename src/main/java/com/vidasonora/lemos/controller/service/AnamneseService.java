package com.vidasonora.lemos.controller.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.entity.Comorbidades;
import com.vidasonora.lemos.model.repository.AnamneseRepository;
import com.vidasonora.lemos.model.repository.ComorbidadesRepository;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository anamneseRepository;
	@Autowired
	private ComorbidadesRepository comorbidadesRepository;
	
	@Transactional
	public Anamnese cadastro(Anamnese anamnese) {
		anamnese.setId(null);
		anamnese = anamneseRepository.save(anamnese);
		Comorbidades comorbidades = comorbidadesRepository.findById(anamnese.getComorbidades().getId()).get();
		comorbidades.setAnamnese(anamnese);
		comorbidadesRepository.save(comorbidades);
		return anamnese;
	}
	
}
