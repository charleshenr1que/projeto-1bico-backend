package com.charleshenrique.project1bico.services;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.charleshenrique.project1bico.domain.Announcement;
import com.charleshenrique.project1bico.domain.User;
import com.charleshenrique.project1bico.dto.AuthorDTO;
import com.charleshenrique.project1bico.repository.AnnouncementRepository;
import com.charleshenrique.project1bico.repository.UserRepository;
import com.charleshenrique.project1bico.util.FileSavingSystem;

import ch.qos.logback.classic.Logger;

@Service
public class AnnouncementService {

	@Autowired
	private AnnouncementRepository repository;

	@Autowired
	private UserRepository repositoryUser;

	@Autowired
	private FileSavingSystem repositoryFile;

	public List<Announcement> findAll() {
		return repository.findAll();
	}

	public Announcement insert(String id, Announcement announcement) {
		Optional<User> user = repositoryUser.findById(id);

		System.out.println("User");
		announcement.setAuthor(new AuthorDTO(user.get()));
		announcement.setDate(LocalDate.now());

		repository.insert(announcement);
		return announcement;
	}

	public String delete(String userId, String annoucementId) {
		Optional<User> user = repositoryUser.findById(userId);
		Optional<Announcement> announcement = repository.findById(annoucementId);
		String result = annoucementId.concat(" Foi deletado com sucesso");
		if (user.get().getId().equals(announcement.get().getAuthor().getId())) {
			System.out.println("User é o dono do annuncio então pode excluir");
			repository.deleteById(annoucementId);
		}

		return result;
	}

	public Announcement findById(String id) {
		Optional<Announcement> announcement = repository.findById(id);
		Announcement anno = announcement.get();
		return anno;
	}

	public Announcement edit(String announcementId, Announcement announcement) throws Exception {
		Optional<Announcement> announcementDTO = repository.findById(announcementId);

		Announcement announcementDto = announcementDTO.get();

		try {
			if (announcement != null && announcementDto != null) {
				repository.save(announcement);
			}
		} catch (Exception e) {
			throw new Exception("Não foi possivel encontrar anuncio" + e);
		}

		return announcement;
	}

	public Announcement editDetalhe(String announcementId, Announcement announcement) throws Exception {
		Optional<Announcement> announcementDTO = repository.findById(announcementId);

		Announcement announcementDto = announcementDTO.get();

		try {
			if (announcement != null && announcementDto != null) {
				announcementDto.setTitle(announcement.getTitle());
				announcementDto.setDescricao(announcement.getDescricao());
				announcementDto.setCidade(announcement.getCidade());
				announcementDto.setEstado(announcement.getEstado());
				announcementDto.setCategoria(announcement.getCategoria());

				repository.save(announcementDto);
			}
		} catch (Exception e) {
			throw new Exception("Não foi possivel encontrar anuncio" + e);
		}

		return announcement;
	}

	public String saveAnnouncementImage(String id, MultipartFile file) throws Exception {
		try {

			String imageName = repositoryFile.save(file, id);

			return imageName;
		} catch (Exception e) {

		}

		return null;
	}

	public String findAnnouncementImage(String imageName) {
		String readImage = "";
		readImage = repositoryFile.readImage(imageName);
		return readImage;
	}

	public List<Announcement> findFromUser(String userId) throws Exception {
		List<Announcement> announcements = repository.findAll();
		List<Announcement> announcementsFromUser = new ArrayList<>();
		for (Announcement announcement : announcements) {
			if (announcement.getAuthor().getId().equals(userId)) {
				announcementsFromUser.add(announcement);
			}
		}
		if (announcementsFromUser.size() <= 0) {
			throw new Exception("Esse Usuario não contem nenhum post");
		}
		return announcementsFromUser;
	}
}
