package com.dlightsaber.member.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "login")
public class Login {

	@NotNull(message = "First Name should not be null")
	@NotEmpty(message = "It should not be empty")
	@Size(max = 30, message = "username should not exceed 30 characters")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Only Characters are allowed")
	private String username;

	@NotBlank
	@NotNull
	@Size(min = 0, max = 8)
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("Login [username=%s, password=%s]", username, password);
	}
}
