package com.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/*
	* Método que busca um usuário pelo seu usuario (email).
	* 
	* select * from tb_usuarios where usuario = "usuario procurado"
	*/
	public Optional<Usuario> findByUsuario (String usuario);
	
	//Método criado para a Sessão de testes
		public List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}


