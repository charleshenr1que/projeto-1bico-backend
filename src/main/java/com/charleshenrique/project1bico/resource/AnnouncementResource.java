package com.charleshenrique.project1bico.resource;

import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLEngineResult.Status;
import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/fromUser/{id}")
	public ResponseEntity<List<Announcement>> findFromUser(@PathVariable String id) {
		List<Announcement> list;
		try {
			list = service.findFromUser(id);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.status(400).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<Announcement> insert(@RequestHeader(value = "id") String id,
			@RequestBody Announcement announcement) {

		service.insert(id, announcement);

		return ResponseEntity.ok().body(announcement);
	}

	@PutMapping
	public ResponseEntity<Announcement> edit(@RequestHeader(value = "id") String id,
			@RequestBody Announcement announcement) throws Exception {

		service.editDetalhe(id, announcement);

		return ResponseEntity.ok().body(announcement);
	}

	@DeleteMapping
	public ResponseEntity<String> delete(@RequestHeader(value = "id") String userId,
			@RequestHeader String annoucementId) {

		String result = service.delete(userId, annoucementId);

		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Announcement> findById(@PathVariable String id) {
		Announcement announcement = service.findById(id);
		System.out.println(announcement);

		return ResponseEntity.ok().body(announcement);
	}

	@PostMapping(value = "/upload")
	public ResponseEntity saveImage(@RequestParam("foto") MultipartFile foto, @RequestParam("id") String id) {
		try {
			Announcement announcement = this.service.findById(id);
			if (announcement != null) {
				String imageUrl = this.service.saveAnnouncementImage(id, foto);
				announcement.setImageUrl(imageUrl);
				this.service.edit(id, announcement);
				return ResponseEntity.ok().body(imageUrl);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (final Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping(value = "/upload/{id}", produces = { "plain/text" })
	public ResponseEntity<String> findImageCatalog(@PathVariable("id") final String id) {
		try {
			Announcement announcement = this.service.findById(id);
			String readImage = this.service.findAnnouncementImage(announcement.getImageUrl());
			return ResponseEntity.ok().body(readImage);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error");
		}
	}

}
