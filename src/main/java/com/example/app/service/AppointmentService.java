package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.domain.Appointment;
import com.example.app.domain.User;
import com.example.app.exception.AppException;
import com.example.app.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public Appointment save(Appointment appointment) {
		
		verifyAvailibility(appointment);

		return appointmentRepository.save(appointment);
	}
	
	
	private void verifyAvailibility(Appointment appointment) {
		
		List<Appointment> list = appointmentRepository.findByDoctorAndStartDate(appointment.getDoctor(), appointment.getStartDate());
		
		if (!list.isEmpty()) {
			throw new AppException(409,"The doctor is not available at this time");
		}
		
	}

	public List<Appointment> findByUser(User user) {
		return this.appointmentRepository.findByUser(user);
	}

	public Optional<Appointment> findById(Long id) {
		return appointmentRepository.findById(id);
	}

}
