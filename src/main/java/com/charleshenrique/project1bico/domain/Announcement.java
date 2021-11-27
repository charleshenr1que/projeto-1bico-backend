package com.charleshenrique.project1bico.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.charleshenrique.project1bico.dto.AuthorDTO;


@Document
public class Announcement {
	@Id
	private String id;
	private String title;
	private String imageUrl;
	private String descricao;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private String categoria;
	private String cidade;
	private String estado;
	
	private AuthorDTO author;
	
	public  Announcement() {
		
	}

	public Announcement(String id, String title, String imageUrl, String descricao, String categoria,
			AuthorDTO author, String cidade, String estado) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.descricao = descricao;
		this.date = LocalDate.now();
		this.categoria = categoria;
		this.author = author;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
}
