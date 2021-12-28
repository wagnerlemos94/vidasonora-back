package com.vidasonora.lemos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vidasonora.lemos.model.entity.Contato;
import com.vidasonora.lemos.model.entity.Pessoa;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

	void deleteByPessoa(Pessoa pessoa);
	
}
