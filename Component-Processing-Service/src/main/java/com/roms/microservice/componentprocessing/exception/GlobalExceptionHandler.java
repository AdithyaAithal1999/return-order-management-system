package com.roms.microservice.componentprocessing.exception;

import java.util.Date; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TokenInvalidException.class)
	public final ResponseEntity<ExceptionResponse> handleTokenInvalidException(TokenInvalidException ex,
			WebRequest request) {
		log.info("INVALID TOKEN: " + ex.getMessage());
		ExceptionResponse e = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ConstraintsInvalidException.class)
	public final ResponseEntity<ExceptionResponse> handleConstraints(ConstraintsInvalidException ex,
			WebRequest request) {
		log.info("ConstraintsInvalidException: " + ex.getMessage());
		ExceptionResponse e = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(PackingAndDeliveryException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(PackingAndDeliveryException ex,
			WebRequest request) {
		log.info("PackingAndDeliveryException occured: " + ex.getMessage());
		ExceptionResponse e = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServiceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleServiceNotFoundException(ServiceNotFoundException ex,
			WebRequest request) {
		log.warn("Service Not found exception: " + ex.getMessage());
		ExceptionResponse e = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		log.info("Exception occured: " + ex.getMessage());
		ExceptionResponse e = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
