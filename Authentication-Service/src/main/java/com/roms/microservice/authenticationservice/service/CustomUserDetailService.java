package com.roms.microservice.authenticationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roms.microservice.authenticationservice.entity.User;
import com.roms.microservice.authenticationservice.exception.UserNotFoundException;
import com.roms.microservice.authenticationservice.model.UserDetailPrincipal;
import com.roms.microservice.authenticationservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> user = repository.findByEmail(email);
		if(user.isEmpty())
			{
			log.warn("User not Found: "+email);
			throw new UserNotFoundException("INVALID_CREDENTIALS");
			}
		
		
		User user2 = user.get();
		log.info("VALID USER: "+email);
		return new UserDetailPrincipal(user2.getId(),user2.getUsername(),user2.getPassword(),user2.getCreditCardNum(),user2.getCreditLimit());
	}

}
