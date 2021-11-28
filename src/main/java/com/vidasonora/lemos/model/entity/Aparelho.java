package com.vidasonora.lemos.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "tb_aparelho")
@Entity
public class Aparelho implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String perda_auditiva;
	private String ducaracaoCrice;
	private String familiaresPerdaAuditiva;
	private String desconfortoSonsIntensos;
	@CollectionTable(name = "tb_sus_particular")
	@ElementCollection
	private List<String> sus_particular;
	@CollectionTable(name = "tb_maiores_dificuldade_auditivas")
	@ElementCollection
	private List<String> maioresDificuldadeAuditivas;
	private String laudoAudiometria;
	private String etilogiaPerdaAuditiva;
	private String satisfeitoAparelho;
	private String somNatural;
	private String doQueNaoGostavaAparelho;
	private String jaTeveProblemas;
	private String doQueGostava;
	private String importandeOuvirMelhor;
	private String porQueAchaUsoAparelhoSeriaUtil;
	private String observacao;
	
	@OneToOne
	@JsonIgnore
	private Anamnese anamnese;
	
}
