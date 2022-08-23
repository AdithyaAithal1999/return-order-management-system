package com.roms.microservice.componentprocessing.controller;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Creating model class for generating authorization token when any customer logs in")
public class AuthResponse {
	@ApiModelProperty(value = "Username of the customer")
	private String userName;
	@ApiModelProperty(value = "Authorization token of the customer")
	private String token;
	@ApiModelProperty(value = "Token Expiration time in milliseconds")
	private String expiresIn;
	@ApiModelProperty(value = "User ID of the customer")
	private String userId;
	    @ApiModelProperty(value = "Credit card number of the Customer")
	    private String creditCardNum;
	    @ApiModelProperty(value = "Credit limit of the Customer")
	    private BigDecimal creditLimit;
}
