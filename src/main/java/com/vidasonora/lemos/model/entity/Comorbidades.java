package com.vidasonora.lemos.model.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Table(name = "tb_comorbidades")
@Entity
public class Comorbidades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ElementCollection
	@CollectionTable(name = "tb_comorbidades_nomes")
	@Column(name = "nome")
	private Set<String> nomes;	
	
	@OneToOne
	@JsonBackReference
	private Anamnese anamnese;
	
}
