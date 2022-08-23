package com.returnordermanag.packaginganddeliveryservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.CorsRegistry;
//import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.returnordermanag.packaginganddeliveryservice.entity.ServiceCharges;
import com.returnordermanag.packaginganddeliveryservice.repository.ServiceChargesDAO;

@SpringBootApplication
public class PackagingAndDeliveryServiceApplication implements CommandLineRunner{

	@Autowired
	private ServiceChargesDAO dao;
	
	public static void main(String[] args) {
		SpringApplication.run(PackagingAndDeliveryServiceApplication.class, args);
	}
 
	@Override
	public void run(String... args) throws Exception {

		
		dao.save(new ServiceCharges("INTEGRAL",new BigDecimal(100),new BigDecimal(200)));
		dao.save(new ServiceCharges("ACCESSORY",new BigDecimal(50),new BigDecimal(100)));
		dao.save(new ServiceCharges("PROTECTIVE SHEATH",new BigDecimal(50),new BigDecimal(0)));
	}

}
