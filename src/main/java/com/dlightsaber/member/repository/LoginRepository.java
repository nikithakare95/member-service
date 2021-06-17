package com.dlightsaber.member.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dlightsaber.member.model.Login;

@Repository
public interface LoginRepository extends MongoRepository<Login, String> {

	Login findByusername(String username);

}
