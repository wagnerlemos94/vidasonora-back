package com.vidasonora.lemos.controller.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidasonora.lemos.model.entity.Perfil;
import com.vidasonora.lemos.model.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Transactional
	public Perfil cadastro(Perfil perfil) {
		perfil.setId(null);
		return perfilRepository.save(perfil);
	}
	
	public List<Perfil> buscarTodos(){
		return perfilRepository.findAll();
	}
	
}
