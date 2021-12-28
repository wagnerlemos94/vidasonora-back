package com.vidasonora.lemos.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_evolucao")
public class Evolucao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeProfissional;
	private String descricao;
	
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-DD-mm")
	private Date data;
	@JsonIgnore
	@CreationTimestamp
	private Date dataCriacao;	
	@JsonBackReference
	@ManyToOne
	private Prontuario prontuario;
	
}
