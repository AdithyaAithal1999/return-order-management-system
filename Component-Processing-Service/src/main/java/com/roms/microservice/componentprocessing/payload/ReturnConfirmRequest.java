package com.roms.microservice.componentprocessing.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@ApiModel(description = "Creating model class to send request id")
public class ReturnConfirmRequest {

	@NonNull
	@ApiModelProperty(value = "Return request Id")
	private Long reqId;
}
