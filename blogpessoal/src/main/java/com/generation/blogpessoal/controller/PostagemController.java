package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.PostagemModel;
import com.generation.blogpessoal.repository.PostagemRepository;

// é preciso colocar no cabeçalho do metodo, algumas anotações para indicar que a classe se trata de um controller


@RestController // indica para o spring que essa classe se trata de controller
@RequestMapping("/postagem") // por qual URL será acessada, a partir do momento que vier uma requisição pelo /postagem,ele consumira essa classe
@CrossOrigin("*") //essa classe irá aceitar requisições de qualquer origem, sendo angular ou react no front-end
public class PostagemController {

	//injetar o nossa classe repository na classe controller
	
	@Autowired // autowired -> instaciação do spring - deixamos toda a responsabilidade de instaciação para o spring
	private PostagemRepository repository;
	
	
	// metodo de findall
	@GetMapping // expor para a api que se trata de um método
				// sempre que vir uma requisição externa atraves url postagem, ira disparar esse método
	public ResponseEntity<List<PostagemModel>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
}
