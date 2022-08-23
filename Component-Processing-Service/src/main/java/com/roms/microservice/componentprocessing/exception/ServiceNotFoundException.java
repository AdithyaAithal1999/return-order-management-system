package com.roms.microservice.componentprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
@Slf4j
public class ServiceNotFoundException extends Exception {
	
	public ServiceNotFoundException(String message) {
		super(message);
		
	}

}
