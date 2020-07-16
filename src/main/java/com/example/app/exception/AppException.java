package com.example.app.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	private int httpStatus;

	private String code;

	private Object details;



	public AppException(String message) {

		this(400, message);

	}

	public AppException(int httpStatus, String message) {

		this(httpStatus, message, null, null);
	}

	public AppException(int httpStatus, String message, String code, Object details) {

		this.httpStatus = httpStatus;
		this.message = message;
		this.code = code;
		this.details = details;

	}

}
