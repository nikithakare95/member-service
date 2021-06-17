package com.dlightsaber.member.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dlightsaber.member.model.Register;
import com.dlightsaber.member.repository.RegistrationRepository;
import com.dlightsaber.member.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationRepository repo;

	@Autowired
	RegistrationService service;

	@GetMapping(path = "/getAllUsers")
	public List<Register> getUser(Register reg) {
		return repo.findAll();
	}

	@Validated
	@PostMapping(path = "/doRegister")
	public ResponseEntity<String> addUserData(@Valid Register reg, BindingResult br) {
		List<String> em = service.getEmailList();
		if (br.hasErrors()) {
			return new ResponseEntity<>("BAD REQUEST.Please provide valid data",HttpStatus.BAD_REQUEST);
		} 
		else {
			if (reg.getPassword().equals(reg.getConfirmPassword())) {
			if (em.contains(reg.getEmail())) {
				return new ResponseEntity<>("User already registered. DUPLICATE EMAIL",HttpStatus.BAD_REQUEST);
			} else {
				repo.save(reg);
			}
			}
			else {
				return new ResponseEntity<>( "Invalid confirm password",HttpStatus.BAD_REQUEST);

		}
		 return new ResponseEntity<>("Registration Done Successfully",HttpStatus.ACCEPTED);
	}
	}

	@DeleteMapping(path = "/deleteByFirstName")
	public String deleteByFirstName(String firstName) {
		List<Register> names = service.getFirstNames(firstName);
		System.out.println(names);
		if (names.isEmpty()) {
			return "No name found";

		} else {
			repo.deleteAll(names);
			return "Deleted User";
		}
	}
}
