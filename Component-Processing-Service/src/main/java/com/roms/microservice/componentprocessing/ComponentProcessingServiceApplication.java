package com.roms.microservice.componentprocessing;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.roms.microservice.componentprocessing.entity.ProcessingCharge;
import com.roms.microservice.componentprocessing.repository.ProcessingChargeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class ComponentProcessingServiceApplication implements CommandLineRunner {

	@Autowired
	private ProcessingChargeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ComponentProcessingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Inserting Processing Charge Details");
		repository.save(new ProcessingCharge("INTEGRAL", 5, new BigDecimal(500)));
		repository.save(new ProcessingCharge("ACCESSORY", 2, new BigDecimal(300)));
		log.info("Processing charge details insertion completed");
	}



	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedOriginPatterns("*").allowedHeaders("*").allowedOriginPatterns("*");
				
				
			}
		};
	}
}
