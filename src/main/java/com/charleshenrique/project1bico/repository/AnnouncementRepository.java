package com.charleshenrique.project1bico.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.charleshenrique.project1bico.domain.Announcement;
import com.charleshenrique.project1bico.domain.User;

@Repository
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {

	@Query("{ id: ?0}")
	List<Announcement>findPostFromUser(String userId);
}
