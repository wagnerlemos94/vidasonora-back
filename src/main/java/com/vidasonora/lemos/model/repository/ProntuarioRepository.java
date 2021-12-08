package com.vidasonora.lemos.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.entity.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long>{

	Optional<Prontuario> findByPessoa(Pessoa pessoa);
	
}
