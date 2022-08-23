package com.returnordermanag.packaginganddeliveryservice.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {

	private String message;
	private String detail;
	private int status;
	private Date timeStamp;
	
}
