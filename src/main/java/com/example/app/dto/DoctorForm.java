package com.example.app.dto;

import javax.validation.constraints.NotNull;

import com.example.app.domain.Doctor;

import lombok.Data;

@Data
public class DoctorForm {

	@NotNull
	private String name;

	@NotNull
	private String speciality;

	public Doctor toDoctor() {

		return Doctor.builder().name(name).speciality(speciality).build();

	}

}
