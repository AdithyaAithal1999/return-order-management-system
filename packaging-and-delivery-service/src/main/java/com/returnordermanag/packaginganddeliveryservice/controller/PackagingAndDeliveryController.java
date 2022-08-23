package com.returnordermanag.packaginganddeliveryservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanag.packaginganddeliveryservice.exception.PackingAndDeliveryException;
import com.returnordermanag.packaginganddeliveryservice.service.PackagingAndDeliveryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value="Get packing and delivery charge endpoints")
@Slf4j
@CrossOrigin
public class PackagingAndDeliveryController {

	@Autowired
	private PackagingAndDeliveryService service;
	
	@GetMapping("/packaginganddeliverycharge/{componentType}/{count}")
	@ApiOperation(value = "Gets Packaging and Delivery Charge", notes = "Depends on Component type and count it calculate the packaging and delivery charge", httpMethod = "POST", response = BigDecimal.class)
	public BigDecimal getPackagingDeliveryCharge( @ApiParam (name = "Component Type", value = "Component type should be Integral or Accessory") @PathVariable String componentType,@ApiParam (name = "Count", value = "Number of components")  @PathVariable int count)  throws PackingAndDeliveryException {
	log.info("Get Delivery charge for:{} count:{}",componentType,count);
		return service.getPackagingDeliveryCharge(componentType, count);

	}
	
	
	@GetMapping("/HealthCheck")
	@ApiOperation(value = "healthCheck", notes = "Check whether microservice is up and running or not", httpMethod = "GET", response = String.class)
	public ResponseEntity<String> healthCheck() {
		log.info("Health-Check: OK");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	
}
