package com.roms.microservice.authenticationservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.roms.microservice.authenticationservice.entity.User;
import com.roms.microservice.authenticationservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Inserting User in DB");
		String roles = "ROLE_USER";
		userRepository.save(new User("Ganesh","ganesh@gmail.com", passwordEncoder.encode("ganeshganesh"),"1234567890123456", new BigDecimal(50000),roles));
		userRepository.save(new User("Lakshmi", "lakshmi@gmail.com",passwordEncoder.encode("lakshmilakshmi"),"2345678901234567", new BigDecimal(50000), roles));
		userRepository.save(new User("Vishnu","vishnu@gmail.com", passwordEncoder.encode("vishnuvishnu"),"4567890123456789", new BigDecimal(55000), roles));
		userRepository.save(new User("Indra", "indra@gmail.com",passwordEncoder.encode("indraindra"),"56789012345167890", new BigDecimal(40000), roles));
		log.info("User are inserted into DB");
	}
	
	@Configuration
	public class WebConfiguration implements WebFluxConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowedMethods("*").allowedHeaders("*")
			.allowedOriginPatterns("*");
	    }
	}

}
