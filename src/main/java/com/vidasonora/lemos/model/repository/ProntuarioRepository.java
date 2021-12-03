package com.vidasonora.lemos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vidasonora.lemos.model.entity.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long>{

	@Query(value = "SELECT * FROM TB_PRONTUARIO p where p.pessoa_id = 1", nativeQuery = true)
	Prontuario buscarProntuarioPorPessoa();
	
}
