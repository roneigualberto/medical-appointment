package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.LoginForm;
import com.example.app.dto.TokenResponse;
import com.example.app.security.JwtService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken authenticationToken = form.toAutenticationToken();

		try {
			Authentication authentication = authManager.authenticate(authenticationToken);
			String token = jwtService.generateToken(authentication);
			return ResponseEntity.ok(new TokenResponse(token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}