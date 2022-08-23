package com.roms.microservice.authenticationservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roms.microservice.authenticationservice.exception.TokenInvalidException;
import com.roms.microservice.authenticationservice.httppayload.AuthResponse;
import com.roms.microservice.authenticationservice.httppayload.LoginRequest;
import com.roms.microservice.authenticationservice.jwtutils.JWTUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@Api(value="Login and Validation endpoints for Authorization Service")
public class AuthenticationController {


	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * @param loginReques: Model has User credentials
	 * @return AuthResponse: If valid user it return user details
	 * @return Exception thrown if not valid user
	 */
	@PostMapping("/login")
	@ApiOperation(value = "customerLogin", notes = "takes customer credentials and generates the unique JWT for each customer", httpMethod = "POST", response = AuthResponse.class)
	
	public ResponseEntity<AuthResponse> authenticateUser( @ApiParam (name = "customerLoginCredentials", value = "Login credentials of the Customer") @RequestBody LoginRequest loginRequest) {
		log.info("Login:" + loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		log.info("Generating Token for: "+loginRequest.getEmail());
		AuthResponse authResponse = jwtUtils.generateJwtToken(authentication);
		log.info("Token generated for: "+loginRequest.getEmail());
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}


	
	/**
	 * @param token:	JWT Token present in Authorization header
	 * @return It return true if user valid
	 * @throws TokenInvalidException if token expired/not-valid throws exception
	 */
	@GetMapping("/validate")
	@ApiOperation(value = "tokenValidation", notes = "returns boolean after validating JWT", httpMethod = "GET", response = Boolean.class)
	public boolean validateAndReturnUser(@RequestHeader("Authorization") String token) throws TokenInvalidException {
		log.info("Validating Token: "+token);
		token=token.substring(7);
		return jwtUtils.validateJwtToken(token);
	}
	
	/**
	 * @return If service is up return OK
	 */
	@GetMapping("/HealthCheck")
	@ApiOperation(value = "healthCheck", notes = "Check whether microservice is up and running or not", httpMethod = "GET", response = String.class)
	public ResponseEntity<String> healthCheck() {
		log.info("Health-Check: OK");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
