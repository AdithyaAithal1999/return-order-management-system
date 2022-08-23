package com.roms.microservice.componentprocessing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.roms.microservice.componentprocessing.exception.ConstraintsInvalidException;
import com.roms.microservice.componentprocessing.exception.ReturnOrderNotFound;
import com.roms.microservice.componentprocessing.exception.TokenInvalidException;
import com.roms.microservice.componentprocessing.feignclient.AuthFeignClient;
import com.roms.microservice.componentprocessing.payload.ReturnConfirmResponse;
import com.roms.microservice.componentprocessing.payload.ReturnRequestPayload;
import com.roms.microservice.componentprocessing.payload.ReturnResponsePayload;
import com.roms.microservice.componentprocessing.service.ReturnComponentService;
import com.roms.microservice.componentprocessing.service.ReturnComponentServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ComponentProcessingServiceCotroller {

	@Autowired
	private AuthFeignClient authFeignClient;
	@Autowired
	private ReturnComponentService service;

	@GetMapping("/HealthCheck")
	@ApiOperation(value = "healthCheck", notes = "Check whether microservice is up and running or not", httpMethod = "GET", response = String.class)
	public ResponseEntity<String> healthCheck() {
		log.info("Health-Check: OK");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	
	@PostMapping("/createReturnRequest")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "createReturnRequest", notes = "Create Return request", httpMethod = "POST", response = ReturnResponsePayload.class)
	public ReturnResponsePayload createReturnRequest(@RequestHeader("Authorization") String token,
			@RequestBody ReturnRequestPayload payload) throws TokenInvalidException, ConstraintsInvalidException {
		log.info("CHECK USER TOKEN: "+token);
		 authFeignClient.validateAndReturnUser(token);
		 log .info("VALID USER");
		log.info("Creating Request:"+payload);
		return service.getReturnComponentCost(payload);
	}


	@PostMapping("/confirmReturn/{reqId}/{card}/{limit}/{charge}")
	public ReturnConfirmResponse confirmReturn(@RequestHeader("Authorization") String token, @PathVariable String reqId)
			throws TokenInvalidException, ReturnOrderNotFound {
		log.info("CHECK USER TOKEN: "+token);
		 authFeignClient.validateAndReturnUser(token);
		log .info("VALID USER");
		return service.returnConfirm(new Long(reqId));
	}

}
