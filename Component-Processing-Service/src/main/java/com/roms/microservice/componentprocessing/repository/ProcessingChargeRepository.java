package com.roms.microservice.componentprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roms.microservice.componentprocessing.entity.ProcessingCharge;

public interface ProcessingChargeRepository extends JpaRepository<ProcessingCharge, Integer>{


	ProcessingCharge findByItemName(String name);
}
