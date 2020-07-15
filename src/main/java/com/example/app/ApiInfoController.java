package com.example.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiInfoController {

	@GetMapping("/api/v1/info")
	public String apiInfo() {

		return "Api Medical Appointment";
	}

}
