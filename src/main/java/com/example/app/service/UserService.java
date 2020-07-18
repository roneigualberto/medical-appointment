package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.domain.User;
import com.example.app.exception.AppException;
import com.example.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		
		verifyEmail(user);

		return userRepository.save(user);
	}
	
	private void verifyEmail(User user) {
		
		Optional<User> optUser = userRepository.findByEmail(user.getEmail());
		
		if (optUser.isPresent()) {
			throw new AppException(409,"This email is already in use.");
		}
	}
	

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

}
