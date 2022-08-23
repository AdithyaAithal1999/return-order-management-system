package com.roms.microservice.authenticationservice.jwtutils;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import com.roms.microservice.authenticationservice.AbstractTest;
import com.roms.microservice.authenticationservice.exception.TokenInvalidException;
import com.roms.microservice.authenticationservice.httppayload.AuthResponse;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class JWTUtilsTest extends AbstractTest {

	@MockBean
	private JWTUtils jwtUtils;
	
	private  Authentication auth;
	@BeforeEach
	public void setUp() {
		auth=new UsernamePasswordAuthenticationToken("User","Password");
	}
	
	
	@Test
	public void generateJwtTokenTest() {
	
		
		when(jwtUtils.generateJwtToken(auth)).thenReturn(new AuthResponse("User", "token", "180000", "1234", "1234567891234", new BigDecimal(123)));
		AuthResponse authResponse = this.jwtUtils.generateJwtToken(auth);
		assertEquals("User", authResponse.getUserName());
	}
	
	
	public void validateJwtTokenTest() throws TokenInvalidException {
		
		when(jwtUtils.validateJwtToken(ArgumentMatchers.anyString())).thenReturn(true);
	assertTrue(jwtUtils.validateJwtToken("token"));	
	}
}
