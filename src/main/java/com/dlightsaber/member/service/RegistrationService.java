package com.dlightsaber.member.service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.dlightsaber.member.controller.RegistrationController;
import com.dlightsaber.member.model.Register;
import com.dlightsaber.member.repository.RegistrationRepository;

@Service
public class RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	@Autowired
	RegistrationRepository repository;
	@Autowired
	MongoTemplate mongotemplate;

	public List<Register> getEmails(String email) {
	List<Register> emailList = repository.findAllByemail(email);
	System.out.println(" email list :" + emailList);
		return emailList;
	}

	public List<String> getPhoneNumbers() {
		List<String> numbers = mongotemplate.findAll(Register.class).stream().map(Register::getPhoneNumber)
				.filter(Objects::nonNull).distinct().collect(Collectors.toList());
		System.out.println("phoneNumberList" + numbers);
		logger.info("List of phoneNumbers :" +numbers);
		return numbers;
	}
	
	public List<String> getEmailList() {
		List<String> emails = mongotemplate.findAll(Register.class).stream().map(Register::getEmail)
				.filter(Objects::nonNull).distinct().collect(Collectors.toList());
		logger.info("List of emails :" +emails);
		return emails;
	}

	public boolean isDuplicatePhoneNumber(Register reg) {
		System.out.println("List of phoneNumbers :" + getPhoneNumbers());
		logger.info("duplicate-number :" +getPhoneNumbers().contains(reg.getPhoneNumber()));
		return getPhoneNumbers().contains(reg.getPhoneNumber());
	}
	
}
