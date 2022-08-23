package com.roms.microservice.componentprocessing.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Creating model class to return response detail")
public class ReturnResponsePayload {
	@ApiModelProperty(value = "Request Id")
    private Long requestId;
	@ApiModelProperty(value = "Processing charge for return order")
    private BigDecimal processingCharge;
	@ApiModelProperty(value = "packaging and delivery charge for return request")
    private BigDecimal packageAndDeliveryCharge;
	@ApiModelProperty(value = "Date of Delivery")
    private LocalDate dateOfDelivery;
}
