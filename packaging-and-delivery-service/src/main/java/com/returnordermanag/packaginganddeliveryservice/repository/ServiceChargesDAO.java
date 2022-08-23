package com.returnordermanag.packaginganddeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.returnordermanag.packaginganddeliveryservice.entity.ServiceCharges;

@Repository
public interface ServiceChargesDAO extends JpaRepository<ServiceCharges, Integer> {

	ServiceCharges findByItemName(String upperCase);

}
