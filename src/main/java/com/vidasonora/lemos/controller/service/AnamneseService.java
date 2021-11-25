package com.vidasonora.lemos.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.entity.DTO.AnamneseInsertDTO;
import com.vidasonora.lemos.model.repository.AnamneseRepository;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository anamneseRepository;
	
	public Anamnese cadastro(AnamneseInsertDTO anamneseDto) {
		anamneseDto.setId(null);
		Anamnese anamnese = anamneseDto.convertToAnamnese(anamneseDto);
		
		System.out.println(anamneseDto);
//		anamnese.setPessoa(pessoaService.buscarPorId(anamnese.getPessoa().getId()));
		return anamneseRepository.save(anamnese);
	}
	
}
