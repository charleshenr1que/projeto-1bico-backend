package com.charleshenrique.project1bico.resource;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charleshenrique.project1bico.domain.Announcement;
import com.charleshenrique.project1bico.domain.User;
import com.charleshenrique.project1bico.services.AnnouncementService;

@RestController
@RequestMapping(value = "/posts")
public class AnnouncementResource {
	
	@Autowired
	private AnnouncementService service;
	

	@GetMapping
	public ResponseEntity<List<Announcement>> findAll() {
		List<Announcement> list = service.findAll();
		list.add(new Announcement(null,"AGORA VAI", null, null, null, null, null));

		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Announcement> insert(@RequestParam(value="username") String username, @RequestBody Announcement announcement) {
		service.insert(username, announcement);
		
		
		return ResponseEntity.ok().body(announcement);
	}
}
