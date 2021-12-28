package com.vidasonora.lemos.model.dto.usuario;

import com.vidasonora.lemos.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioConsultaDTO {
	
	private Long id;	
	private String nome;
	private String email;
	private String status;
	private String perfil;
	
	public UsuarioConsultaDTO converteToDto(Usuario usuario) {
		String status = "ATIVO";
		if(usuario.getStatus() != 1) {
			status = "INATIVO";
		}
		UsuarioConsultaDTO dto = new UsuarioConsultaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(),
				status, usuario.getPerfil().getNome());		
		return dto;
	}
}
