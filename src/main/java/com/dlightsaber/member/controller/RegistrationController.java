package com.dlightsaber.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlightsaber.member.model.Register;
import com.dlightsaber.member.repository.RegistrationRepository;
import com.dlightsaber.member.service.RegistrationService;

@RestController
public class RegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegistrationRepository repo;

	@Autowired
	RegistrationService service;

	@PostMapping(path = "/register")
	public ResponseEntity<Object> addUserData(@Valid @RequestBody Register reg, BindingResult br) {

		try {
			if (br.hasErrors()) {

				return new ResponseEntity<>("{\r\n" + "    \"isError\": True,\r\n"
						+ "    \"message\": \"Please provide valid input data\"\r\n" + "    \"Details\":"
						+ getAllErrors(reg, br) + "\r\n " + " }", HttpStatus.BAD_REQUEST);
			}

			else {
				if (reg.getPassword().equals(reg.getConfirmPassword()) && !service.isDuplicatePhoneNumber(reg)) {
					List<String> em = service.getEmailList();
					logger.info("List of emails :" +em);
					if (em.contains(reg.getEmail())) {
						logger.warn("Duplicate email");
						return new ResponseEntity<>("{\r\n" + "    \"isRegister\": False,\r\n"
								+ "    \"message\": \"User is already registered. Duplicate email\" :\r\n" + "}",
								HttpStatus.BAD_REQUEST);
						
					} else {
						repo.save(reg);
					}
				} else {
					return new ResponseEntity<>(
							"{\r\n" + "    \"isError \": True,\r\n"
									+ "    \"message\": \"Invalid confirm password or duplicate phoneNumber\" :\r\n"
									+ "    \"isDuplicateNumber\":" + service.isDuplicatePhoneNumber(reg) + " :\r\n"
									+ "    \"invalidConfirmPassword\":"
									+ !reg.getPassword().equals(reg.getConfirmPassword()) + " :\r\n" + "}",
							HttpStatus.BAD_REQUEST);

				}
			}
			logger.info("Registration Successful");
			return new ResponseEntity<>("{\r\n" + "    \"isRegistered\": True,\r\n"

					+ "    \"message\": \"Registration done sucessfully\" :\r\n" + "}", HttpStatus.ACCEPTED);

		} catch (Exception e) {
			logger.error("Invalid input request for sign-up");
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
	}

	public Map<String, String> getAllErrors(@Valid @RequestBody Register reg, BindingResult br) {
		
		System.out.println(br.getAllErrors());
		List<FieldError> errors = br.getFieldErrors();	
		System.out.println(errors);
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (FieldError fieldError : errors) {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			hashmap.put(field, message);
			System.out.println(hashmap);
			logger.info("All the errors related to input request :" +hashmap);
		}
		
		return hashmap;
	}
}
