package com.vidasonora.lemos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vidasonora.lemos.model.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
