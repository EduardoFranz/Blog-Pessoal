package com.generation.blogpessoal.model;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* para essa classe ser interpretada pelo banco de dados,para o jpa/hibernate consiga entender como uma entidade
precisamos inserir algumas anotações,
anotações são parametros que colocamos em cima das classes e proproeidades que definem uma certa tipo de comportamento*/


@Entity //ele indica essa classe como entidade do jpa/hibernate
@Table(name = "tb_postagens")  //que essa entidade dentro do banco de dados vai virar uma tabela
public class Postagem {

	@Id //definir o id da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // esse atributo vai ser tornar primary key
	private Long id;
	
	@NotBlank//o campo titulo não pode ser nulo
	@Size(min = 5, max = 100) //determinara quantidade de caracteres que o cliente consegue enviar como titulo
	private String titulo;
	
	@NotNull//o campo texto não pode ser nulo
	@Size(min = 5, max = 500) //determinara quantidade de caracteres que o cliente consegue enviar como texto
	private String texto;
	
	
	@UpdateTimestamp //-> Anotação que pega automaticamente hora e data do seu computador
	private LocalDateTime data ;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	//Relacionamento entre as tabelas, fazer com que tema e postagem crie relacionamento
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;	
	



	//getters e setters da aplicação
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
