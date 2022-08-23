package com.roms.microservice.componentprocessing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.roms.microservice.componentprocessing.exception.FeignErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {




	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}
}
