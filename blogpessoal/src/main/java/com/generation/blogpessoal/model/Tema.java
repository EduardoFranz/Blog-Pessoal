package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



//transformar em uma entidade do jpa/hibernate
@Entity
@Table(name = "tb_tema")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String descricao;
															   // cascade --> todas as postagens serao alteradas em conjuntos, todas postagens referente ele sofrerão alteçaõ
	@OneToMany(mappedBy = "tema" ,cascade = CascadeType.ALL)  //mappedBy --> qual classe,tabela,atributo estamos mapeando
	@JsonIgnoreProperties("tema") //
	
	private List<PostagemModel> postagem;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Getters e Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<PostagemModel> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<PostagemModel> postagem) {
		this.postagem = postagem;
	}
	
	
}


	