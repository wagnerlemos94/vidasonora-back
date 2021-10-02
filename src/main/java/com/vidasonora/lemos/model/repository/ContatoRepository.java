package com.vidasonora.lemos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vidasonora.lemos.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
