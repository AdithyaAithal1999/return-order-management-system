package com.roms.microservice.componentprocessing.service;

import com.roms.microservice.componentprocessing.exception.ConstraintsInvalidException;
import com.roms.microservice.componentprocessing.exception.ReturnOrderNotFound;
import com.roms.microservice.componentprocessing.payload.ReturnConfirmResponse;
import com.roms.microservice.componentprocessing.payload.ReturnRequestPayload;
import com.roms.microservice.componentprocessing.payload.ReturnResponsePayload;

public interface ReturnComponentService {
	public ReturnResponsePayload getReturnComponentCost(ReturnRequestPayload payload) throws ConstraintsInvalidException;
	public ReturnConfirmResponse returnConfirm(Long reqId) throws ReturnOrderNotFound;
}
