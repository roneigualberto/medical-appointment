package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.domain.Doctor;
import com.example.app.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor save(Doctor doctor) {

		return doctorRepository.save(doctor);
	}

	public  List<Doctor> findAll() {
		return this.doctorRepository.findAll();
	}
	
	public Optional<Doctor> findById(Long id) {
		return doctorRepository.findById(id);
	}

}
