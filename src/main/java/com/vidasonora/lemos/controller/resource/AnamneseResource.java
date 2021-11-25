package com.vidasonora.lemos.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidasonora.lemos.controller.service.AnamneseService;
import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.entity.DTO.AnamneseInsertDTO;

@RestController
@RequestMapping(value = "anamneses")
public class AnamneseResource {
	
	@Autowired
	private AnamneseService anamneseService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Anamnese> cadastro(@RequestBody AnamneseInsertDTO anamneseDto){
		Anamnese anamnese = anamneseService.cadastro(anamneseDto);
		return ResponseEntity.ok().body(anamnese);
	}

}
