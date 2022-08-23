package com.roms.microservice.componentprocessing.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Creating model class for return request detail")
public class ReturnRequestPayload {
    @ApiModelProperty(value = "Username of the Customer")
    private String name;
    @ApiModelProperty(value = "Contact Number of the user")
    private Long contactNumber;
   
    private DefectiveComponentDetail defectiveComponentDetail;

}

