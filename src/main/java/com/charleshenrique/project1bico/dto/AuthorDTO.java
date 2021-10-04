package com.charleshenrique.project1bico.dto;

import com.charleshenrique.project1bico.domain.User;

public class AuthorDTO {
	private String id;
	private String fullName;

	public AuthorDTO() {
		
	}

	public AuthorDTO(User obj) {
		id = obj.getId();
		fullName = obj.getFullName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
