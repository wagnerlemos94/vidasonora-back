package com.vidasonora.lemos.model.entity.DTO;

import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.entity.Pessoa;

import lombok.Data;

@Data
public class AnamneseInsertDTO {
	
	private Long id;
	private String queixaPrincipal;
	private String solicitante;
	private String encaminhadoPor;
	private String preferenciaManunal;
	private Pessoa pessoa;
	
	
	public Anamnese convertToAnamnese(AnamneseInsertDTO anamneseDTO) {
		Anamnese anamnese = new Anamnese(anamneseDTO.getId(), anamneseDTO.getQueixaPrincipal(), anamneseDTO.getSolicitante(), 
				anamneseDTO.getEncaminhadoPor(), anamneseDTO.getPreferenciaManunal(), anamneseDTO.getPessoa());
		return anamnese;
		
	}
	
}
