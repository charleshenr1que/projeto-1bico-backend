package com.charleshenrique.project1bico.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.charleshenrique.project1bico.domain.Login;
import com.charleshenrique.project1bico.domain.User;
import com.charleshenrique.project1bico.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	

	@GetMapping(value="/all")
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody User user) {
		service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//buscando um dado com RequestParam
	@GetMapping
	public ResponseEntity<Optional<User>> findByName(@RequestParam(value="userName") String userName) {
		//userName = URL.decodeParam(userName);
		Optional<User> list = service.findByName(userName);
		System.out.println(userName);
		return ResponseEntity.ok().body(list);
	}
	

	//Buscando um dado como path
	//@GetMapping(value ="/{name}")
	//public ResponseEntity<<User>> findByNamePara(@PathVariable String name) {
	//	//userName = URL.decodeParam(userName);
	//	List<User> list = service.findByName(name);
	//	System.out.println(name);
	//	return ResponseEntity.ok().body(list);
	//}
	
	@PostMapping(value="/log")
	public ResponseEntity<User> login(@RequestBody Login login) {
	System.out.println("------------");
	User user = service.findByNameAndPassword(login.getUserName(), login.getPassword());
	if(user != null) {
	return ResponseEntity.ok().body(user);
	}
	return ResponseEntity.badRequest().body(null);

	}

}
