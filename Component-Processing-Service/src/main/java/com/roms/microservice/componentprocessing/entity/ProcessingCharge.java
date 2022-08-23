package com.roms.microservice.componentprocessing.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ProcessingCharge {

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "Process Charge id")
	private int id;
	@NonNull
	@ApiModelProperty(value = "Component Name")
	private String itemName;
	@NonNull
	@ApiModelProperty(value = "Number of days required for processing")
	private int processingDays;

	@NonNull
	@ApiModelProperty(value = "Processing charge for return order")
	private BigDecimal processingFee;
	@CreationTimestamp
	@ApiModelProperty(value = "Inserted timestamp")
	private Date createdDate;
	
	@UpdateTimestamp
	@ApiModelProperty(value = "Updated timestamp")
	private Date updatedDate;
}
