package com.charleshenrique.project1bico.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.charleshenrique.project1bico.dto.AuthorDTO;


@Document
public class Announcement {
	@Id
	private String id;
	private String title;
	private String imageUrl;
	private String descricao;
	private Instant date;
	private String categoria;
	
	private AuthorDTO author;
	
	public  Announcement() {
		
	}

	public Announcement(String id, String title, String imageUrl, String descricao, Date date, String categoria,
			AuthorDTO author) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.descricao = descricao;
		this.date = Instant.now();
		this.categoria = categoria;
		this.author = author;
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

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
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
	
	
}
