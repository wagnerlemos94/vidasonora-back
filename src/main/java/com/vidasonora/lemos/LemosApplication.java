package com.vidasonora.lemos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vidasonora.lemos.model.entity.Cidade;
import com.vidasonora.lemos.model.entity.Estado;
import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.EstadoRepository;
import com.vidasonora.lemos.model.repository.UsuarioRepository;

@SpringBootApplication
public class LemosApplication implements CommandLineRunner{
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(LemosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario = new Usuario(null, "admin", "admin", 1);		
		usuarioRepository.save(usuario);
		
		Cidade cidade1 = new Cidade(null, "Salvador");
		Cidade cidade2 = new Cidade(null, "Lauro de Freitas");
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades.addAll(Arrays.asList(cidade1, cidade2));
		
		Estado estado1 = new Estado(null, "BAHIA", "BA", cidades);
		cidade1.setEstado(estado1);
		cidade2.setEstado(estado1);
		estadoRepository.save(estado1); 
	}

}
