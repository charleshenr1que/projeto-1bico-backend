package com.charleshenrique.project1bico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charleshenrique.project1bico.domain.Announcement;
import com.charleshenrique.project1bico.domain.User;
import com.charleshenrique.project1bico.dto.AuthorDTO;
import com.charleshenrique.project1bico.repository.AnnouncementRepository;
import com.charleshenrique.project1bico.repository.UserRepository;

@Service
public class AnnouncementService {
	
	@Autowired
	private AnnouncementRepository repository;
	
	@Autowired
	private UserRepository repositoryUser;
	
	
	public List<Announcement> findAll(){
		return repository.findAll();
	}
	
	public Announcement insert(String name, Announcement announcement) {
		Optional<User> user = repositoryUser.findByUserName(name);
		
		System.out.println("User");
		announcement.setAuthor(new AuthorDTO(user.get()));
		
		repository.insert(announcement);
		return announcement;
	}
	
}
