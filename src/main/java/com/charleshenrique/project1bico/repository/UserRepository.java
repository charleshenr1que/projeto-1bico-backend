package com.charleshenrique.project1bico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.charleshenrique.project1bico.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	

	Optional<User> findByUserName(String userName);
	
	@Query("{ userName: ?0 , password: ?1 }")
	User findByUserNameAndPassword(String userName, String password);
	
	@Query("{ userName: ?0}")
	List<User>findPassword(String userName);
	

}
