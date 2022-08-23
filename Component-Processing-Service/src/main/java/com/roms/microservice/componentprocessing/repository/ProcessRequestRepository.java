package com.roms.microservice.componentprocessing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roms.microservice.componentprocessing.entity.ProcessRequest;

public interface ProcessRequestRepository extends JpaRepository<ProcessRequest, Long> {
Optional<ProcessRequest>	findByRequestId(long id);
}
