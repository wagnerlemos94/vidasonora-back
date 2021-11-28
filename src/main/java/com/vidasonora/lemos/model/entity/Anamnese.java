package com.vidasonora.lemos.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_anamnese")
@Entity
public class Anamnese implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String queixaPrincipal;
	private String solicitante;
	private String encaminhadoPor;
	private String preferenciaManunal;
	
	@OneToOne(mappedBy = "anamnese",cascade = CascadeType.ALL)
	private Comorbidade comorbidade;
	
	@OneToOne(mappedBy = "anamnese",cascade = CascadeType.ALL)
	private QueixasAuditiva queixasAuditiva;
	
	@OneToOne(mappedBy = "anamnese",cascade = CascadeType.ALL)
	private Tontura tontura;
	
	@OneToOne(mappedBy = "anamnese",cascade = CascadeType.ALL)
	private Zumbido zumbido;
	
	@OneToOne(mappedBy = "anamnese",cascade = CascadeType.ALL)
	private Aparelho aparelho;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
	
	public void relacionaComorbidade(Anamnese anamnese) {
		comorbidade = anamnese.getComorbidade();
		comorbidade.setAnamnese(anamnese);
	}
	
	private void relacionaQueixasAuditiva(Anamnese anamnese) {
		queixasAuditiva = anamnese.getQueixasAuditiva();
		queixasAuditiva.setAnamnese(anamnese);
	}

	private void relacionaTontura(Anamnese anamnese) {
		tontura = anamnese.getTontura();
		tontura.setAnamnese(anamnese);		
	}

	private void relacionaZumbido(Anamnese anamnese) {
		zumbido = anamnese.getZumbido();
		zumbido.setAnamnese(anamnese);		
	}
	
	private void relacionaAparelho(Anamnese anamnese) {
		aparelho = anamnese.getAparelho();
		aparelho.setAnamnese(anamnese);		
	}
	
	public void relacionaEntidades(Anamnese anamnese) {
		relacionaComorbidade(anamnese);
		relacionaQueixasAuditiva(anamnese);
		relacionaTontura(anamnese);
		relacionaZumbido(anamnese);
		relacionaAparelho(anamnese);
	}
	
}
