package com.roms.microservice.authenticationservice.filter;

import java.io.IOException; 

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;
import com.roms.microservice.authenticationservice.exception.TokenInvalidException;
import com.roms.microservice.authenticationservice.jwtutils.JWTUtils;
import com.roms.microservice.authenticationservice.service.CustomUserDetailService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@NoArgsConstructor
public class AuthenticationInterceptorFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private CustomUserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = getJWTTokenFromRequest(request);
		try {
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = userDetailService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("User Login Failed {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String getJWTTokenFromRequest(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (!Strings.isNullOrEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}

		return null;
	}

}
