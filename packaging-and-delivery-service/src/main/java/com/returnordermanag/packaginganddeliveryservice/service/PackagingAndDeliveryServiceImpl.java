package com.returnordermanag.packaginganddeliveryservice.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnordermanag.packaginganddeliveryservice.entity.ServiceCharges;
import com.returnordermanag.packaginganddeliveryservice.exception.PackingAndDeliveryException;
import com.returnordermanag.packaginganddeliveryservice.repository.ServiceChargesDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PackagingAndDeliveryServiceImpl implements PackagingAndDeliveryService {

	@Autowired
	private ServiceChargesDAO dao;

	public BigDecimal getPackagingDeliveryCharge(String type, int count) throws PackingAndDeliveryException {
		ServiceCharges ps = dao.findByItemName("PROTECTIVE SHEATH");
		ServiceCharges sc = null;
		if (count <= 0) {
			log.error("Component count should be greater than zero: {}", count);
			throw new PackingAndDeliveryException("Item count should be greater than zero");
		}
		if (type.equalsIgnoreCase("INTEGRAL") && !type.isEmpty())
			sc = dao.findByItemName(type.toUpperCase());
		else if (type.equalsIgnoreCase("ACCESSORY") && !type.isEmpty())
			sc = dao.findByItemName(type.toUpperCase());
		else {
			log.error("Invalid Component type: " + type);
			throw new PackingAndDeliveryException("INVALID_COMPONENT_TYPE");
		}

		return sc.getCost().multiply(new BigDecimal(count)).add(ps.getCost()).add(sc.getDeliveryCharge());
	}

}
