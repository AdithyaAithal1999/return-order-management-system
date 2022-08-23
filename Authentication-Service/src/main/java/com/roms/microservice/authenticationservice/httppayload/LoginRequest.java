package com.roms.microservice.authenticationservice.httppayload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Creating model class for customer login")
public class LoginRequest {
	@ApiModelProperty(value = "E-Mail of the Customer")
    private String email;
	@ApiModelProperty(value = "Password of the Customer")
	private String password;
}
