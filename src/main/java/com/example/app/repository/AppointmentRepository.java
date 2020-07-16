package com.example.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.domain.Appointment;
import com.example.app.domain.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findByUser(User user);

}
