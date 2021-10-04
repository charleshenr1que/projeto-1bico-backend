package com.charleshenrique.project1bico.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charleshenrique.project1bico.domain.User;
import com.charleshenrique.project1bico.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository reposit;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		

		
	}

}
