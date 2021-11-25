package com.vidasonora.lemos.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.repository.AnamneseRepository;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository anamneseRepository;
	@Autowired
	private PessoaService pessoaService;
	
	public Anamnese cadastro(Anamnese anamnese) {
		anamnese.setId(null);
		System.out.println(anamnese);
//		anamnese.setPessoa(pessoaService.buscarPorId(anamnese.getPessoa().getId()));
		return anamneseRepository.save(anamnese);
	}
	
}
