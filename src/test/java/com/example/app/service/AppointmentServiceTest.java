package com.example.app.service;

import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.app.domain.Appointment;
import com.example.app.domain.Doctor;
import com.example.app.domain.User;
import com.example.app.exception.AppException;
import com.example.app.repository.AppointmentRepository;


@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {

	@InjectMocks
	private AppointmentService appointmentService;

	@Mock
	private AppointmentRepository appointmentRepository;

	@Test
	public void shouldSaveAppointment() {

		Appointment appointment = Appointment.builder().doctor(new Doctor()).user(new User())
				.startDate(LocalDateTime.now()).build();

		given(appointmentRepository.findByDoctorAndStartDate(Mockito.any(), Mockito.any()))
				.willReturn(new ArrayList<>());

		appointmentService.save(appointment);

	}
	
	@Test(expected = AppException.class)
	public void shouldNotSaveAppointmentWithConflict() {

		Appointment appointment = Appointment.builder().doctor(new Doctor()).user(new User())
				.startDate(LocalDateTime.now()).build();
		

		List<Appointment> appointments = Arrays.asList(appointment);
		

		given(appointmentRepository.findByDoctorAndStartDate(Mockito.any(), Mockito.any()))
				.willReturn(appointments);

		appointmentService.save(appointment);

	}

}
