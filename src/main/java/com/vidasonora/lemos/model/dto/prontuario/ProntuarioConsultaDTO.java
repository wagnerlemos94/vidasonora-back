package com.vidasonora.lemos.model.dto.prontuario;

import java.util.List;

import com.vidasonora.lemos.model.entity.Anamnese;
import com.vidasonora.lemos.model.entity.Prontuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioConsultaDTO {
	
	private Long id;
	private String nomeCliente;
	private List<Anamnese> anamneses;
	
	public ProntuarioConsultaDTO convertToProntuario(Prontuario prontuario) {
		return new ProntuarioConsultaDTO(
				prontuario.getId(),prontuario.getPessoa().getNome(),prontuario.getAnamneses());
	}
}
