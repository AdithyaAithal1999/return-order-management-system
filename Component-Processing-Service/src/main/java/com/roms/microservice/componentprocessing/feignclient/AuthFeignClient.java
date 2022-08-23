package com.roms.microservice.componentprocessing.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.roms.microservice.componentprocessing.exception.TokenInvalidException;

@FeignClient(url = "${AUTHSERVICEURL:http://localhost:8055}",name = "authentication-service")
public interface AuthFeignClient {

	@GetMapping(value ="/auth/validate")
	
	public boolean validateAndReturnUser(@RequestHeader("Authorization") String token) throws TokenInvalidException;
}
