package com.example.app.dto;

import java.time.LocalDateTime;

import com.example.app.domain.Appointment;
import com.example.app.domain.Doctor;
import com.example.app.domain.User;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class AppointmentForm {

	@NotNull
	private Long doctorId;

	@NotNull
	private LocalDateTime startDate;

	public Appointment toAppointment(User user) {
		return new Appointment(null, new Doctor(doctorId), user, startDate);
	}

}
