package com.charleshenrique.project1bico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charleshenrique.project1bico.domain.User;
import com.charleshenrique.project1bico.repository.UserRepository;
import com.google.gson.Gson;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository repository;

	public List<User> findAll() {
		
		return repository.findAll();
	}
	
	public User insert(User user) {
		
		return repository.insert(user);
	}
	
	public Optional<User> findByName(String userName){
		return repository.findByUserName(userName);
	}
	
	public User findByNameAndPassword(String userName, String password) {
		
		System.out.println("KD:");
		System.out.println(userName +" " + password);
		User user = repository.findByUserNameAndPassword(userName, password);
		if(user != null) {
			System.out.println("Não é nulo");
			return user;
		}
		System.out.println("É nulo");
		return null;
	}
	public List<User> findByPassword(String userName){
		return repository.findPassword(userName);
	}
	
}
