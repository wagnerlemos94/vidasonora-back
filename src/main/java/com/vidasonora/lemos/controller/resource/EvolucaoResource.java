package com.vidasonora.lemos.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidasonora.lemos.controller.service.EvolucaoService;
import com.vidasonora.lemos.model.entity.Evolucao;

@RestController
@RequestMapping(value = "/evolucoes")
public class EvolucaoResource {

	@Autowired
	private EvolucaoService evolucaoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Evolucao> cadastro(@RequestBody Evolucao evolucao){
		evolucao = evolucaoService.cadastro(evolucao);
		return ResponseEntity.ok().body(evolucao);
	}
	
	
}
