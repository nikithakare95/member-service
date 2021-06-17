package com.dlightsaber.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlightsaber.member.model.Login;
import com.dlightsaber.member.repository.LoginRepository;
import com.dlightsaber.member.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginRepository repo;

	@Autowired
	LoginService service;

	@PostMapping("/login")
	public ResponseEntity<String> login( @RequestBody Login log) {
		try {
			String message = null;
			Boolean validCredentials = service.checkCredentials(log);
			if (validCredentials) {
				message = "{\r\n" + "    \"isLogin\": true,\r\n" + "    \"username\": \"+memberlogin\"\r\n"
						+ "    \"message\": \"User login successful.\"\r\n" + "}";
			}
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\r\n" + "    \"isLogin\": false,\r\n"
					+ "    \"message\": \"Username or Password is Wrong.\"\r\n" + "}", HttpStatus.BAD_REQUEST);
		}
	}
}