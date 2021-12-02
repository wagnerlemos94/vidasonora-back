package com.vidasonora.lemos.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String rg;
	private String profissao;
	private Integer status;
	
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nascimento;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Contato> contatos;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	
	@OneToOne(mappedBy = "pessoa")
	private Prontuario prontuario;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Anamnese> anamneses;
	
	public Pessoa(Long id, String nome, String cpf, String rg, String profissao, Date nascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.profissao = profissao;
		this.nascimento = nascimento;
	}
	public Pessoa(Long id, String nome, String cpf, String rg, String profissao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.profissao = profissao;
	}
	

}
