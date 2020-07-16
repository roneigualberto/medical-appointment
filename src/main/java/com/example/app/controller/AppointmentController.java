package com.example.app.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Appointment;
import com.example.app.domain.User;
import com.example.app.dto.AppointmentForm;
import com.example.app.dto.AppointmentResponse;
import com.example.app.exception.AppException;
import com.example.app.service.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	
	
	public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid AppointmentForm form) {

		User user = (User) getAuthentication().getPrincipal();

		Appointment appointment = form.toAppointment(user);

		Appointment appointmentSaved = appointmentService.save(appointment);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(appointmentSaved.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping
	public ResponseEntity<?> list() {

		User user = (User) getAuthentication().getPrincipal();

		List<Appointment> appointments = appointmentService.findByUser(user);

		List<AppointmentResponse> response = AppointmentResponse.from(appointments);

		return ResponseEntity.ok(response);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {

		Optional<Appointment> optAppointment = appointmentService.findById(id);

		if (!optAppointment.isPresent()) {
			throw new AppException(404, "Doctor not found");
		}

		Appointment appointment = optAppointment.get();

		AppointmentResponse response = new AppointmentResponse(appointment);

		return ResponseEntity.ok(response);

	}

}
