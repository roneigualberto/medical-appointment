package com.example.app.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.domain.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

	private Long id;

	private String name;

	private String speciality;

	private LocalDateTime createdAt;

	public DoctorResponse(Doctor doctor) {
		this.id = doctor.getId();
		this.name = doctor.getName();
		this.speciality = doctor.getSpeciality();
		this.createdAt = doctor.getCreatedAt();
	}

	public static List<DoctorResponse> from(List<Doctor> doctors) {

		return doctors.stream().map((doctor) -> new DoctorResponse(doctor)).collect(Collectors.toList());
	}

}
