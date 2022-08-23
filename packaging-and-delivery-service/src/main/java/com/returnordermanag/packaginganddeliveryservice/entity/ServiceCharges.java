package com.returnordermanag.packaginganddeliveryservice.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@ApiModel(description = "Creating model class for storing packing and delivery charge")
public class ServiceCharges {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@ApiModelProperty(value = "Component Name ")
	private String itemName;
@NonNull
@ApiModelProperty(value = "Packing charge")
	private BigDecimal cost;
@NonNull
@ApiModelProperty(value = "Delivery charge")
	private BigDecimal deliveryCharge;
}
