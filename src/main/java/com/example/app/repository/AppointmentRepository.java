package com.example.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.domain.Appointment;
import com.example.app.domain.Doctor;
import com.example.app.domain.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findByUser(User user);
	
	public List<Appointment> findByDoctorAndStartDate(Doctor doctor, LocalDateTime startDate);

}
