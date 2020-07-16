package com.example.app.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.User;
import com.example.app.dto.UserForm;
import com.example.app.service.UserService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid UserForm userForm) {

		User user = userForm.toUser();

		User userSalved = userService.save(user);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(userSalved.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

}
