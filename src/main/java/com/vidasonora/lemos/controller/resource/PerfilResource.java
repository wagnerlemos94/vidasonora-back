package com.vidasonora.lemos.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidasonora.lemos.controller.service.PerfilService;
import com.vidasonora.lemos.model.entity.Perfil;

@RestController
@RequestMapping(value = "perfis")
public class PerfilResource {
	
	@Autowired
	private PerfilService perfilService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Perfil> cadastro(@RequestBody Perfil perfil){
		perfil = perfilService.cadastro(perfil);
		return ResponseEntity.ok().body(perfil);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Perfil>> buscarTodos(){
		List<Perfil> perfis = perfilService.buscarTodos();
		return ResponseEntity.ok().body(perfis);
	}

}
