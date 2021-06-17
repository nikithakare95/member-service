package com.dlightsaber.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.dlightsaber.member.model.Login;
import com.dlightsaber.member.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	MongoTemplate mongotemplate;

	@Autowired
	LoginRepository repo;

	public Boolean checkCredentials(Login login)  {
		Login user = repo.findByusername(login.getUsername());
		if (user == null) {
			throw new RuntimeException("Exception in LoginService due to invalid Username");
		}
		if (!user.getPassword().equals(login.getPassword())) {
			throw new RuntimeException("Exception in LoginService due to invalid password");
		}
		return (null != user && user.getPassword().equals(login.getPassword()));
	}
}
