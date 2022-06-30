package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.PostagemModel;
import com.generation.blogpessoal.repository.PostagemRepository;

// é preciso colocar no cabeçalho do método, algumas anotações para indicar que a classe se trata de um controller


@RestController // indica para o spring que essa classe se trata de controller
@RequestMapping("/postagem") // por qual URL será acessada, a partir do momento que vier uma requisição pelo /postagem,ele consumira essa classe
@CrossOrigin("*") //essa classe irá aceitar requisições de qualquer origem, sendo angular ou react no front-end
public class PostagemController {

	//injetar  nossa classe repository na classe controller
	
	@Autowired // autowired -> instaciação do spring - deixamos toda a responsabilidade de instacia para o spring
	private PostagemRepository repository;
	
	
	// findall
	@GetMapping // expor para a api que se trata de um método
				// sempre que vir uma requisição externa atraves url postagem, ira disparar esse método
	public ResponseEntity<List<PostagemModel>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//findall by id
	//get passando por parametro id
	@GetMapping("/{id}")						//@PathVariable -> para esse metodo consiga capturar valor que vira pela url exemplo:/postagem/id
	public ResponseEntity<PostagemModel> GetById(@PathVariable long id){
		return repository.findById(id) //collection
				.map(resp -> ResponseEntity.ok(resp)) // para pegar resposta positiva  da collection
				.orElse(ResponseEntity.notFound().build()); // caso resposta venha nula retorna um notfound
		
	}
	
	
	//find by titulo
	//dentro das chaves passa o atributo especifico
	@GetMapping("/titulo/{titulo}") //sub-rota
	public ResponseEntity<List<PostagemModel>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		// trazer tudo de acordo com que digitamos
	}
	
	@PostMapping
	public ResponseEntity<PostagemModel> post (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	
	@PutMapping
	public ResponseEntity<PostagemModel> put (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
