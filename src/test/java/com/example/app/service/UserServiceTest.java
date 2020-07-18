package com.example.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.app.domain.User;
import com.example.app.exception.AppException;
import com.example.app.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void shouldSaveUser() {

		User user = User.builder()
				.name("Ronei Macedo Gualberto")
				.email("ronei.mac.rmg@gmail.com")
				.password("pwd123").build();
		
		
		User userRet = User.builder()
				.name("Ronei Macedo Gualberto")
				.id(1l)
				.email("ronei.mac.rmg@gmail.com")
				.password("pwd123").build();

		given(userRepository.findByEmail(Mockito.anyString())).willReturn(Optional.empty());
		
		given(userRepository.save(Mockito.any())).willReturn(userRet);
		
		User userSaved = userService.save(user);
		
		assertThat(userSaved.getId(), is(userRet.getId()));

	}
	
	@Test(expected = AppException.class)
	public void shouldNotSaveUserWithConclictEmail() {

		User user = User.builder()
				.name("Ronei Macedo Gualberto")
				.email("ronei.mac.rmg@gmail.com")
				.password("pwd123").build();
		
		
		User userRet = User.builder()
				.name("Ronei Macedo Gualberto")
				.id(1l)
				.email("ronei.mac.rmg@gmail.com")
				.password("pwd123").build();

		given(userRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(userRet));
		
		
		userService.save(user);
		

	}

}
