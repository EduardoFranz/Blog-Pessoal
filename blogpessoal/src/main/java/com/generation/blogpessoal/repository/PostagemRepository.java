package com.generation.blogpessoal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.PostagemModel;

/* extends de uma outra interface chamada jpa
 os parametros que serão qual tipo de entidade estamos trabalhando PostagemModel e qual o tipo do id Long*/
@Repository // essa classe se trata de uma classe de repositorio
public interface PostagemRepository extends JpaRepository<PostagemModel, Long>{
	
	//metodos query
	
	//metodo inteiro -> consulta pelo titulo da postagem,
	//FindAllBy  -> buscar todos pelo titulo dentro da classe PostagemModel entidade titulo
	//Containing -> seria a mesma coisa do LIKE do SQL, tudo que conter os caracteres dentro dessa variavel ele vai trazer
	//IgnoreCase -> utilizado para ignorar letra maiscula e minuscula
	public List<PostagemModel> findAllByTituloContainingIgnoreCase(String título);
	
	
}











