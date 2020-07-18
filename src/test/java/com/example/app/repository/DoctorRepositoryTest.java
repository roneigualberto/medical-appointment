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

import com.example.app.domain.Doctor;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorRepositoryTest {

	@Autowired
	private DoctorRepository doctorRepository;

	private Doctor doctor;

	@Before
	public void setUp() {

		doctor = Doctor.builder().name("Doctor").speciality("pediatrics").build();

		doctor = doctorRepository.saveAndFlush(doctor);

	}

	@Test
	public void shouldSaveDoctor() {

		Doctor newDoctor = Doctor.builder().name("Doctor Two").speciality("cardiologist").build();

		Doctor doctorSaved = doctorRepository.saveAndFlush(newDoctor);

		assertThat(doctorSaved.getId(), notNullValue());

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotSaveDoctorWithNullFields() {

		Doctor doctor = Doctor.builder().name(null).speciality(null).build();

		doctorRepository.saveAndFlush(doctor);

	}

	@After
	public void tearDown() {
		doctorRepository.delete(doctor);
	}

	@Test
	public void shouldRetrieveDoctorByID() {

		Optional<Doctor> doctorFind = doctorRepository.findById(doctor.getId());

		assertThat(doctorFind.isPresent(), is(true));
	}

}
