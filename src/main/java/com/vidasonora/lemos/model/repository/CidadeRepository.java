package com.vidasonora.lemos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vidasonora.lemos.model.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	Cidade findByNome(String nome);
}
