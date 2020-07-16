package com.example.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.app.domain.User;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UserForm {

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String password;

	public User toUser() {
		
		String passwordEncoded = new BCryptPasswordEncoder().encode(password);

		return new User(null, name, email, passwordEncoded);
	}

}
