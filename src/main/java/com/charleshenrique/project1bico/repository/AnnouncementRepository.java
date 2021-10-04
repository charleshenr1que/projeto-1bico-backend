package com.charleshenrique.project1bico.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.charleshenrique.project1bico.domain.Announcement;

@Repository
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {

}
