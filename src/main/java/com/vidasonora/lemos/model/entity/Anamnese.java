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
	private Comorbidades comorbidades;
	
	@OneToOne(mappedBy = "anamnese",cascade = CascadeType.ALL)
	private QueixasAuditivas queixasAuditivas;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
	
}
