package com.vidasonora.lemos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vidasonora.lemos.model.entity.Evolucao;

public interface EvolucaoRepository extends JpaRepository<Evolucao, Long>{

	@Query(value = "SELECT * FROM TB_EVOLUCAO WHERE PRONTUARIO_ID = ?1",nativeQuery = true)
	List<Evolucao> buscarTodosPorIdProntuario(Long id);

}
