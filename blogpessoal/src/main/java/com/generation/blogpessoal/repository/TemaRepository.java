
/*Essa Interface serve para fazer um methody query(métodos personalizados),
 * para fazer consultas personzalizadas para buscar pelo tema
 * 
 */

package com.generation.blogpessoal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.blogpessoal.model.Tema;




public interface TemaRepository extends JpaRepository<Tema , Long> {
	
	//de acordo com o que o cliente digitar vai pegar tudo que contem aquela palavra que ele digitou e ignora letra maiscula e minuscula
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}

	