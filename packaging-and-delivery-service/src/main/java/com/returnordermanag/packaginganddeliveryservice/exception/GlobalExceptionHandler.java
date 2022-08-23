package com.returnordermanag.packaginganddeliveryservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(PackingAndDeliveryException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(PackingAndDeliveryException ex, WebRequest request) {
        ExceptionResponse e = new ExceptionResponse( ex.getMessage(), request.getDescription(false),
                HttpStatus.NOT_FOUND.value(),new Date());
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse e = new ExceptionResponse( ex.getMessage(), request.getDescription(false),
                HttpStatus.NOT_FOUND.value(),new Date());
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

}
