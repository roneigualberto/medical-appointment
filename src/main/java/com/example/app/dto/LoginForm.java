package com.example.app.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class LoginForm {

	@NotNull
	private String email;

	@NotNull
	private String password;

	public UsernamePasswordAuthenticationToken toAutenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
