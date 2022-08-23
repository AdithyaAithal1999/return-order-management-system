package com.roms.microservice.componentprocessing.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@ApiModel(description = "Creating model class to return confirm response")
public class ReturnConfirmResponse {

	@NonNull
	@ApiModelProperty(value = "Return confirmation")
	private boolean isConfirmed;
}
