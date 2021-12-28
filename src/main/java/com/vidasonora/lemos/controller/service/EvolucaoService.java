package com.vidasonora.lemos.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.model.entity.Evolucao;
import com.vidasonora.lemos.model.repository.EvolucaoRepository;

@Service
public class EvolucaoService {

	@Autowired
	private EvolucaoRepository evolucaoRepository;
	
	public Evolucao cadastro(Evolucao evolucao) {
		evolucao.setId(null);
		return evolucaoRepository.save(evolucao);
	}
	
	public List<Evolucao> buscarTodos(){
		return evolucaoRepository.findAll();
	}
	
	public List<Evolucao> buscarTodosPorIdProntuario(Long id){
		return evolucaoRepository.buscarTodosPorIdProntuario(id);
	}
}
