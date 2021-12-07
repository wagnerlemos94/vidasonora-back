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
@Table(name = "tb_tontura")
@Entity
public class Tontura implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String inicio;
	private String ocorrencia;
	private String intencidade;
	@ElementCollection
	@CollectionTable(name = "tb_tontura_tendencia_queda")
	private List<String> tendenciaQueda;
	@ElementCollection
	@CollectionTable(name = "tb_tontura_duracao_crise")
	private List<String> duracaoCrise;
	@ElementCollection
	@CollectionTable(name = "tb_tontura_duracao_surge_ou_piora")
	private List<String> surgeOuPiora;
	@ElementCollection
	@CollectionTable(name = "tb_tontura_sensacao")
	private List<String> sensacao;
	@ElementCollection
	@CollectionTable(name = "tb_tontura_surge_piora")
	private List<String> surgePiora;
	@ElementCollection
	@CollectionTable(name = "tb_tontura_desvio_marcha")
	private List<String> desvioMarcha;
	private String fatoresDesecadeantes;
	private String fatoresAgravantes;
	private String fatoresMelhora;
	private String sintomasConcomitantes;
	private String outrosDados;
	
	@OneToOne
	@JsonIgnore
	private Anamnese anamnese;
	
}
