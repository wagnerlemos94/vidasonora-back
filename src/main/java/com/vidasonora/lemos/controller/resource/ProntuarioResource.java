package com.vidasonora.lemos.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidasonora.lemos.controller.service.ProntuarioService;
import com.vidasonora.lemos.controller.service.exception.ObjetoNaoEncontrado;
import com.vidasonora.lemos.model.dto.prontuario.ProntuarioConsultaDTO;

@RequestMapping(value = "prontuarios")
@RestController
public class ProntuarioResource {
	
	@Autowired
	private ProntuarioService prontuarioService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		try {			
			ProntuarioConsultaDTO prontuario = prontuarioService.buscarPorId(id);
			return ResponseEntity.ok().body(prontuario);
		} catch (ObjetoNaoEncontrado e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
