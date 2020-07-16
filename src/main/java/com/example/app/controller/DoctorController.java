package com.example.app.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Doctor;
import com.example.app.dto.DoctorForm;
import com.example.app.dto.DoctorResponse;
import com.example.app.exception.AppException;
import com.example.app.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid DoctorForm doctorForm) {

		Doctor doctor = doctorForm.toDoctor();

		Doctor doctorSaved = doctorService.save(doctor);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(doctorSaved.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping
	public ResponseEntity<?> list() {

		List<Doctor> doctors = doctorService.findAll();

		List<DoctorResponse> response = DoctorResponse.from(doctors);

		return ResponseEntity.ok(response);

	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {

		Optional<Doctor> optDoctor = doctorService.findById(id);

		if (!optDoctor.isPresent()) {
			throw new AppException(404,"Doctor not found");
		}

		Doctor doctor = optDoctor.get();

		DoctorResponse response = new DoctorResponse(doctor);

		return ResponseEntity.ok(response);

	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,
				@Valid @RequestBody DoctorForm doctorForm) {

		Doctor doctor = doctorForm.toDoctor();

		Optional<Doctor> optDoctor = doctorService.findById(id);

		if (!optDoctor.isPresent()) {
			throw new AppException(404,"Doctor not found");
		}

		doctor.setId(id);

		doctorService.save(doctor);

		DoctorResponse response = new DoctorResponse(doctor);

		return ResponseEntity.ok(response);

	}



}
