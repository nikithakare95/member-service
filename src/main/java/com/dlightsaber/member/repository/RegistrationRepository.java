package com.dlightsaber.member.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dlightsaber.member.model.Register;

@Repository
public interface RegistrationRepository extends MongoRepository<Register, String> {
	List<Register> findAllByemail(String email);
}
