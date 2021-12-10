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
@Table(name = "tb_zumbido")
@Entity
public class Zumbido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Intensidade;
	private String Frequencia;
	private String Pitch;
	private String dificuldadeDormir;
	private String algumTratamento;
	private String observacao;
	@ElementCollection
	@CollectionTable(name = "tb_zumbido_Tipo")
	private List<String> tipo;
	@ElementCollection
	@CollectionTable(name = "tb_zumbido_com_que_parece")
	private List<String> comQueParece;
	
	@OneToOne
	@JsonIgnore
	private Anamnese anamnese;
	
}
