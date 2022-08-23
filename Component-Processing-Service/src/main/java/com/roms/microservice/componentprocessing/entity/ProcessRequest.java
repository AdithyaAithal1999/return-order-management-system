package com.roms.microservice.componentprocessing.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "Creating model class for creating process request")
public class ProcessRequest {

	@Id
	@GeneratedValue
	private int id;
	
	@NonNull
	@ApiModelProperty(value = "Request Id")
	private Long requestId;
	@NonNull
	@Size(min = 3, max = 30, message = "Name size should be 3 - 30 chracters")
	@ApiModelProperty(value = "Username of the Customer")
	private String userName;
	@NonNull
	@Size(min = 10 , max = 10, message = "Contact Number should contain exactly 10 digits")
	@Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$",message = "Only Numbers")
	@ApiModelProperty(value = "Contact number of the Customer")
	private String contactNumber;
	@NonNull
	@ApiModelProperty(value = "Component type: Integral/Accessory")
	private String componentType;
	@NonNull
	@ApiModelProperty(value = "Component Name")
	private String componentName;
	@NonNull
	@Min(value = 1,message = "Quantity should be greater than 1")@Max(value = 999,message = "Quantity should be lesser than 999")
	@ApiModelProperty(value = "Number of components")
	private Integer quantity;
	
	@NonNull
	@ApiModelProperty(value = "Status of the request")
	private String status;
	@NonNull
	@Min(value = 1)
	@ApiModelProperty(value = "Processing charge for return request")
	private BigDecimal processingCharge;
	@NonNull
	@Min(1)
	@ApiModelProperty(value = "packaging and delivery charge for return request")
	private BigDecimal packageAndDeliveryCharge;
	@Future
	@ApiModelProperty(value = "Date of Delivery")
	private LocalDate deliveryDate;

}
