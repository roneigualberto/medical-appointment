package com.example.app.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

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

import com.example.app.domain.User;
import com.example.app.dto.LoginForm;
import com.example.app.dto.TokenResponse;
import com.example.app.dto.UserForm;
import com.example.app.security.JwtService;
import com.example.app.service.UserService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid UserForm userForm) {

		User user = userForm.toUser();

		User userSalved = userService.save(user);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(userSalved.getId()).toUri();
		
		TokenResponse response = generateTokenResponse(userForm);

		return ResponseEntity.created(location)
				.body(response);
				

	}
	
	public TokenResponse generateTokenResponse(UserForm form) {
		UsernamePasswordAuthenticationToken authenticationToken = 	new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());

			Authentication authentication = authManager.authenticate(authenticationToken);
			String token = jwtService.generateToken(authentication);
			return new TokenResponse(token);
		
	}

}
