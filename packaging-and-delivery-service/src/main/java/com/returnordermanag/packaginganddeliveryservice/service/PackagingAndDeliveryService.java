package com.returnordermanag.packaginganddeliveryservice.service;

import java.math.BigDecimal;

import com.returnordermanag.packaginganddeliveryservice.exception.PackingAndDeliveryException;

public interface PackagingAndDeliveryService {
	public BigDecimal getPackagingDeliveryCharge(String type, int count) throws PackingAndDeliveryException;
}
