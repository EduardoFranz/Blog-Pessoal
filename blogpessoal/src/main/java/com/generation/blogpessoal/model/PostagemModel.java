package com.generation.blogpessoal.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* para essa classe ser interpretada pelo banco de dados,para o jpa/hibernate consiga entender como uma entidade
precisamos inserir algumas anotações,
anotações são parametros que colocamos em cima das classes e proproeidades que definem uma certa tipo de comportamento*/


@Entity //ele indica essa classe como entidade do jpa/hibernate
@Table(name = "postagem")  //que essa entidade dentro do banco de dados vai virar uma tabela
public class PostagemModel {

	@Id //definir o id da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // esse atributo vai ser tornar primary key
	private long id;
	
	@NotNull//o campo titulo não pode ser nulo
	@Size(min = 5, max = 100) //determinara quantidade de caracteres que o cliente consegue enviar como titulo
	private String titulo;
	
	@NotNull//o campo texto não pode ser nulo
	@Size(min = 5, max = 500) //determinara quantidade de caracteres que o cliente consegue enviar como texto
	private String texto;
	
	
	//@UpdateTimestamp -> Anotação que pega automaticamente hora e data do seu computador
	@Temporal(TemporalType.TIMESTAMP)  //indicar para o jpa/hibernate que estamos trabalhando com tempo
	private Date data = new java.sql.Date(System.currentTimeMillis()); //para capturar exatamente a data, quando um dado passar por essa clase
	//trocar Date por LocalDateTime data,maneira diferente de testar data
	
	
	//Relacionamento entre as tabelas, fazer com que tema e postagem crie relacionamento
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	
	
	
	
	//getters e setters da aplicação
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	
	
	
	
}
