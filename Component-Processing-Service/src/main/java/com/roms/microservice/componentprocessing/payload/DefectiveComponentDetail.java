package com.roms.microservice.componentprocessing.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@ApiModel(description = "Creating model class for defective components detail")
public class DefectiveComponentDetail {
	@NonNull
	@ApiModelProperty(value = "Component type: Integral/Accessory")
	private String componentName;
	@NonNull
	@ApiModelProperty(value = "Component Name")
	private String componentType;
	@NonNull
	@ApiModelProperty(value = "Number of components")
	private Integer quantity;
	
}




