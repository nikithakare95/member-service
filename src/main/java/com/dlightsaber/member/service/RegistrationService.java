package com.dlightsaber.member.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.dlightsaber.member.model.Register;
import com.dlightsaber.member.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	RegistrationRepository repository;
	@Autowired
	MongoTemplate mongotemplate;

	public List<Register> getFirstNames(String firstName) {
		List<Register> firstNamesList = repository.findByfirstName(firstName);
		System.out.println(firstNamesList);
		return firstNamesList;
	}

	public List<Register> getLastNames(String lastName) {
		List<Register> lastNamesList = repository.findByfirstName(lastName);
		System.out.println(lastNamesList);
		return lastNamesList;
	}

	public List<String> getEmailList() {

		List<String> emails = mongotemplate.findAll(Register.class).stream().map(Register::getEmail)
				.filter(Objects::nonNull).distinct().collect(Collectors.toList());

		System.out.println(emails);

		return emails;
	}

	public boolean checkPasswordAndConfirmPassword(Register reg) {
		return reg.getPassword().equals(reg.getConfirmPassword());

	}

}
