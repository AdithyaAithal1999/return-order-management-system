package com.roms.microservice.componentprocessing.feignclient;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.roms.microservice.componentprocessing.exception.PackingAndDeliveryException;

@FeignClient(url = "${PACKAGINGANDDELIVERYSERVICEURL:http://localhost:9055}",name="packaging-and-delivery")
public interface PackingAndDeliveryFeignClient {
	
	@GetMapping("/packaginganddelivery/packaginganddeliverycharge/{componentType}/{count}")
	public BigDecimal getPackagingDeliveryCharge(@PathVariable String componentType, @PathVariable int count)  throws PackingAndDeliveryException ;
}
