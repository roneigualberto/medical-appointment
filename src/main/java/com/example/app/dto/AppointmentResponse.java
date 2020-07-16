package com.example.app.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.domain.Appointment;

import lombok.Data;

@Data
public class AppointmentResponse {

	private Long id;

	private DoctorResponse doctor;

	private LocalDateTime startDate;

	public AppointmentResponse(Appointment appointment) {
		this.id = appointment.getId();
		this.doctor = new DoctorResponse(appointment.getDoctor());
		this.startDate = appointment.getStartDate();
	}

	public static List<AppointmentResponse> from(List<Appointment> appointments) {

		return appointments.stream()
				.map((appointment) -> new AppointmentResponse(appointment)).collect(Collectors.toList());
	}

}
