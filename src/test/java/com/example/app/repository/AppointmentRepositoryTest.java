package com.example.app.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.app.domain.Appointment;
import com.example.app.domain.Doctor;
import com.example.app.domain.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppointmentRepositoryTest {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	private Doctor doctor;

	private User user;

	private Appointment appointment;

	@Before
	public void setUp() {

		doctor = Doctor.builder().name("Doctor").speciality("pediatrics").build();

		doctor = doctorRepository.saveAndFlush(doctor);

		user = User.builder().name("Ronei Macedo Gualberto").email("ronei.mac.rmg@gmail.com").password("pwd123")
				.build();

		userRepository.saveAndFlush(user);

		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

		appointment = Appointment.builder().doctor(doctor).user(user).startDate(tomorrow).build();

		appointmentRepository.saveAndFlush(appointment);

	}

	@Test
	public void shouldSaveAppointment() {

		LocalDateTime today = LocalDateTime.now();

		appointment = Appointment.builder().doctor(doctor).user(user).startDate(today).build();

		Appointment appointmentSaved = appointmentRepository.saveAndFlush(appointment);
		
		
		assertThat(appointmentSaved.getId(), notNullValue());
		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotSaveAppointmentWithConflict() {


		Appointment newAppointment = Appointment.builder().doctor(doctor)
				
				.user(user).startDate(appointment.getStartDate()).build();

		appointmentRepository.saveAndFlush(newAppointment);
		
		
		
	}
	
	@Test
	public void shouldRetrieveAppointmentByDoctorAndStartDate() {



		List<Appointment> appointments = appointmentRepository.findByDoctorAndStartDate(doctor, appointment.getStartDate());
		
		
		assertThat(appointment.getId(), is(appointments.get(0).getId()));
		
		
		
	}
	
	
	@After
	public void tearDown() {
		appointmentRepository.deleteAll();
		doctorRepository.delete(doctor);
		userRepository.delete(user);
	}

	
	
	

}
