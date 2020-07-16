package com.example.app.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiResponse {

	private Date timestamp;

	private int status;
	
	private String type;
	
	private String code;

	private String message;
	
	private Object details;

}
