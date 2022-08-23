package com.roms.microservice.authenticationservice.jwtutils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.roms.microservice.authenticationservice.exception.TokenInvalidException;
import com.roms.microservice.authenticationservice.httppayload.AuthResponse;
import com.roms.microservice.authenticationservice.model.UserDetailPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class JWTUtils {
	  @Value("${app.jwtSecret}")
	    private String jwtSecret;

	    @Value("${app.jwtExpirationMs}")
	    private int jwtExpirationMs;

	    public AuthResponse generateJwtToken(Authentication authentication) {

	        UserDetailPrincipal userPrincipal = (UserDetailPrincipal) authentication.getPrincipal();

	        return new AuthResponse(userPrincipal.getUsername(), Jwts.builder()
	                .setSubject(userPrincipal.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	                .signWith(SignatureAlgorithm.HS256, jwtSecret)
	                .compact(),String.valueOf(jwtExpirationMs),String.valueOf(userPrincipal.getId()),userPrincipal.getCreditCardNum(),userPrincipal.getCreditLimit());
	    }

	    public String getUserNameFromJwtToken(String token) {
	        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateJwtToken(String authToken) throws TokenInvalidException {
	        String msg = "INVALID_TOKEN";
			try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	            return true;
	        } catch (SignatureException e) {
	            log.error("Invalid JWT signature: {}", e.getMessage());
	            throw new TokenInvalidException(msg);
	        } catch (MalformedJwtException e) {
	            log.error("Invalid JWT token: {}", e.getMessage());
	            throw new TokenInvalidException(msg);
	        } catch (ExpiredJwtException e) {
	            log.error("JWT token is expired: {}", e.getMessage());
	            throw new TokenInvalidException(msg);
	        } catch (UnsupportedJwtException e) {
	            log.error("JWT token is unsupported: {}", e.getMessage());
	            throw new TokenInvalidException(msg);
	        } catch (IllegalArgumentException e) {
	            log.error("JWT claims string is empty: {}", e.getMessage());
	            throw new TokenInvalidException(msg);
	        }
	    }
}
