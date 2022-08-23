package com.roms.microservice.authenticationservice.controller;

import java.util.Date;

import javax.management.ServiceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roms.microservice.authenticationservice.exception.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FallbackController {

	  @GetMapping("/defaultFallback")
	    public ResponseEntity<ExceptionResponse> defaultMessage() throws ServiceNotFoundException
	    {
		  System.out.println("SNA");
		  ExceptionResponse e = new ExceptionResponse(new Date(), "SERVICE NOT FOUND", "SERVICE NOT FOUND",
	                HttpStatus.SERVICE_UNAVAILABLE.value());
	        return new ResponseEntity<ExceptionResponse>(e, HttpStatus.SERVICE_UNAVAILABLE);
		
	    }
	  
	  @PostMapping("/defaultFallback")
	    public ResponseEntity<ExceptionResponse> defaultPostMessage() throws ServiceNotFoundException
	    {
		  System.out.println("SNA");
		  ExceptionResponse e = new ExceptionResponse(new Date(), "SERVICE NOT FOUND", "SERVICE NOT FOUND",
	                HttpStatus.SERVICE_UNAVAILABLE.value());
		  return new ResponseEntity<ExceptionResponse>(e, HttpStatus.SERVICE_UNAVAILABLE);
	    
	    }
}
