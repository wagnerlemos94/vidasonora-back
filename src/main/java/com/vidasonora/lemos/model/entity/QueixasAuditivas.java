package com.vidasonora.lemos.model.entity;

import java.io.Serializable;
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

import lombok.Data;

@Data
@Table(name = "tb_queixas_Auditivas")
@Entity
public class QueixasAuditivas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CollectionTable(name = "tb_queixas_auditivas_nomes")
	@Column(name = "nome")
	@ElementCollection
	private Set<String> nomes;
	@CollectionTable(name = "tb_queixas_auditivas_ouvido_direito")
	@ElementCollection
	private Set<String> ouvidoDireito;
	@CollectionTable(name = "tb_queixas_auditivas_ouvido_esquerdo")
	@ElementCollection
	private Set<String> ouvidoEsquerdo;
	
	@OneToOne
	private Anamnese anamnese;
	
}
