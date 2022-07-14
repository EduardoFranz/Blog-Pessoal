package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

// é preciso colocar no cabeçalho do método, algumas anotações para indicar que a classe se trata de um controller


@RestController // indica para o spring que essa classe se trata de controller
@RequestMapping("/postagens") // por qual URL será acessada, a partir do momento que vier uma requisição pelo /postagem,ele consumira essa classe
@CrossOrigin(origins = "*", allowedHeaders = "*")//essa classe irá aceitar requisições de qualquer origem, sendo angular ou react no front-end
public class PostagemController {

	//injetar  nossa classe repository na classe controller
	
	@Autowired // autowired -> instaciação do spring - deixamos toda a responsabilidade de instacia para o spring
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	// findall
	@GetMapping // expor para a api que se trata de um método
				// sempre que vir uma requisição externa atraves url postagem, ira disparar esse método
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	//findall by id
	//get passando por parametro id
	@GetMapping("/{id}")						//@PathVariable -> para esse metodo capturar valor que ira vir pela url exemplo:/postagem/id
	public ResponseEntity<Postagem> getById (@PathVariable Long id){
		return postagemRepository.findById(id) //collection
				.map(resposta -> ResponseEntity.ok(resposta)) // para pegar resposta positiva  da collection
				.orElse(ResponseEntity.notFound().build()); // caso resposta venha nula retorna um notfound
		
	}
	
	
	//find by titulo
	//dentro das chaves passa o atributo especifico
	@GetMapping("/titulo/{titulo}") //sub-rota
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		// trazer tudo de acordo com que digitamos
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){

		if (temaRepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	
	}
	
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
		
			if (postagemRepository.existsById(postagem.getId())){
			
			if (temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem (@PathVariable Long id) {
		
		return postagemRepository.findById(id)
				.map(resposta -> {
					postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
}
