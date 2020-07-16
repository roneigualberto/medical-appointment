package com.example.app.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(AppException.class)
	public final ResponseEntity<ApiResponse> handleUserException(AppException ex, WebRequest request) {

		int httpStatus = ex.getHttpStatus() != 0 ? ex.getHttpStatus() : 400;

		ApiResponse response = new ApiResponse(new Date(), ex.getHttpStatus(), "APPLICATION_ERRO", ex.getCode(),
				ex.getMessage(), null);

		return ResponseEntity.status(httpStatus).body(response);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldErro> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(erro -> new FieldErro(erro.getField(), erro.getDefaultMessage())).collect(Collectors.toList());

		ApiResponse response = new ApiResponse(new Date(), status.value(), "VALIDATION_ERROR", null,
				"Validation Errors", errors);

		return ResponseEntity.status(status).body(response);
	}
}
