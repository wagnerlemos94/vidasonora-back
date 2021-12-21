package com.vidasonora.lemos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vidasonora.lemos.controller.service.PerfilService;
import com.vidasonora.lemos.controller.service.PessoaService;
import com.vidasonora.lemos.controller.service.UsuarioService;
import com.vidasonora.lemos.model.entity.Cidade;
import com.vidasonora.lemos.model.entity.Contato;
import com.vidasonora.lemos.model.entity.Endereco;
import com.vidasonora.lemos.model.entity.Estado;
import com.vidasonora.lemos.model.entity.Perfil;
import com.vidasonora.lemos.model.entity.Pessoa;
import com.vidasonora.lemos.model.entity.Usuario;
import com.vidasonora.lemos.model.repository.EstadoRepository;

@SpringBootApplication
public class LemosApplication implements CommandLineRunner{
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PessoaService pessoaService;	
	@Autowired
	private PerfilService perfilService;

	public static void main(String[] args) {
		SpringApplication.run(LemosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Perfil perfil = new Perfil(null,"ADMIN");
		
		perfilService.cadastro(perfil);
		
		Usuario usuario = new Usuario(null,"Aline Cupertino Lemos" , "admin", "admin", 1, perfil);		

		usuarioService.cadastro(usuario);
		
		Cidade cidade1 = new Cidade(null, "Salvador");
		Cidade cidade2 = new Cidade(null, "Lauro de Freitas");
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades.addAll(Arrays.asList(cidade1, cidade2));
		
		Estado estado1 = new Estado(null, "BAHIA", "BA", cidades);
		cidade1.setEstado(estado1);
		cidade2.setEstado(estado1);
		estadoRepository.save(estado1); 
		
		
		Pessoa pessoa = new Pessoa(null, "Wagner lemos", "3124598700", "12457896321", "Analista de sistemas", sdf.parse("01/06/1994"));
		
		Contato contato1 = new Contato(null, "CELULAR","7113246579", pessoa);
		Contato contato2 = new Contato(null, "EMAIL","contato@contato.com", pessoa);
		
		Endereco endereco1 = new Endereco(null, "41250000", "QUalquer", "de cima", "0000", "Sem complemento", pessoa, cidade2);
		
		pessoa.setContatos(Arrays.asList(contato1,contato2));
		pessoa.setEnderecos(Arrays.asList(endereco1));
				
		pessoaService.cadastro(pessoa);
	}

}
