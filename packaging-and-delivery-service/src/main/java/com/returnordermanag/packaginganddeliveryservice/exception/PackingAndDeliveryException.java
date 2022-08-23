package com.returnordermanag.packaginganddeliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Invalid Packing and Delivery details")
public class PackingAndDeliveryException extends RuntimeException {

	public PackingAndDeliveryException(String message) {
		super(message);
	}

}
