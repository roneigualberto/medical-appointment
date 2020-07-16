package com.example.app.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.app.domain.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	private static final String USER_EMAIL = "ronei.mac.rmg@gmail.com";

	@Before
	public void setUp() {

		user = User.builder().name("Ronei Macedo Gualberto").email(USER_EMAIL).password("pwd123").build();

		userRepository.saveAndFlush(user);

	}

	@Test
	public void shouldSaveUser() {

		User newUser = User.builder().name("New User").email("email@example.com").password("pwd123").build();

		User userSaved = userRepository.saveAndFlush(newUser);

		assertThat(userSaved.getId(), notNullValue());

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotSaveUserWithNullFields() {

		User newUser = User.builder().name(null).email(null).password(null).build();

		userRepository.saveAndFlush(newUser);

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotSaveUserWithDuplicateEmail() {

		User newUser = User.builder().name("Ronei Gualberto").email("ronei.mac.rmg@gmail.com").password("pwd123456")
				.build();

		User userSaved = userRepository.saveAndFlush(newUser);

		userRepository.saveAndFlush(userSaved);

	}

	@Test
	public void shouldRetrieveUserByEmail() {

		Optional<User> userFind = userRepository.findByEmail(USER_EMAIL);

		assertThat(userFind.isPresent(), is(true));
	}

	@Test
	public void shouldNotRetrieveUserByEmail() {

		Optional<User> userFind = userRepository.findByEmail("not-found@email.com");

		assertThat(userFind.isPresent(), is(false));
	}

	@After
	public void tearDown() {
		userRepository.delete(user);
	}

}
