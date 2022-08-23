package com.roms.microservice.componentprocessing.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roms.microservice.componentprocessing.entity.ProcessRequest;
import com.roms.microservice.componentprocessing.entity.ProcessingCharge;
import com.roms.microservice.componentprocessing.exception.ConstraintsInvalidException;
import com.roms.microservice.componentprocessing.exception.PackingAndDeliveryException;
import com.roms.microservice.componentprocessing.exception.ReturnOrderNotFound;
import com.roms.microservice.componentprocessing.feignclient.PackingAndDeliveryFeignClient;
import com.roms.microservice.componentprocessing.payload.ReturnConfirmResponse;
import com.roms.microservice.componentprocessing.payload.ReturnRequestPayload;
import com.roms.microservice.componentprocessing.payload.ReturnResponsePayload;
import com.roms.microservice.componentprocessing.repository.ProcessRequestRepository;
import com.roms.microservice.componentprocessing.repository.ProcessingChargeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReturnComponentServiceImpl implements ReturnComponentService{

	@Autowired
	private ProcessRequestRepository processRequestRepository;
	@Autowired
	private ProcessingChargeRepository chargeRepository;
	@Autowired
	private PackingAndDeliveryFeignClient client;

	public ReturnResponsePayload getReturnComponentCost(ReturnRequestPayload payload)
			throws ConstraintsInvalidException {
		int count = payload.getDefectiveComponentDetail().getQuantity();
		if (count < 0) {
			log.error("Component count should be greater than zero: {}", count);
			throw new PackingAndDeliveryException("INVALID_QUANTITY");
		}
		ReturnResponsePayload responsePayload = new ReturnResponsePayload();

		ProcessingCharge processingCharge = chargeRepository
				.findByItemName(payload.getDefectiveComponentDetail().getComponentType().toUpperCase());

		if (processingCharge == null) {
			log.error("Invalid Compoent type: {}", payload.getDefectiveComponentDetail().getComponentType());
			throw new PackingAndDeliveryException("INVALID_COMPONENT_TYPE");
		}

		responsePayload.setPackageAndDeliveryCharge(
				client.getPackagingDeliveryCharge(payload.getDefectiveComponentDetail().getComponentType(),
						payload.getDefectiveComponentDetail().getQuantity()));

		responsePayload.setProcessingCharge(processingCharge.getProcessingFee());
		responsePayload.setDateOfDelivery(LocalDate.now().plusDays(processingCharge.getProcessingDays()));

		Random random = new Random();
		long requestId = random.nextInt(999999 - 1000) + (long) 1000;
		responsePayload.setRequestId(requestId);
		while (processRequestRepository.findByRequestId(requestId).isPresent()) {
			requestId = random.nextInt(999999 - 1000) + (long) 1000;
			responsePayload.setRequestId(requestId);
		}

		ProcessRequest entity = new ProcessRequest(requestId, payload.getName(),
				String.valueOf(payload.getContactNumber()), payload.getDefectiveComponentDetail().getComponentType(),
				payload.getDefectiveComponentDetail().getComponentName(),
				payload.getDefectiveComponentDetail().getQuantity(), "PENDING", responsePayload.getProcessingCharge(),
				responsePayload.getPackageAndDeliveryCharge());
		try {
			processRequestRepository.save(entity);
			log.info("Process request created: "+entity);
		} catch (Exception e) {
			log.error("Validation Failed: {}", e.getMessage());
			throw new ConstraintsInvalidException("VALIDATION FAILED");
		}
		return responsePayload;

	}

	public ReturnConfirmResponse returnConfirm(Long reqId) throws ReturnOrderNotFound {

		Optional<ProcessRequest> returnOrder = processRequestRepository.findByRequestId(reqId);
		if (returnOrder.isEmpty()) {
			log.error("Request ID:{} not found", reqId);
			throw new ReturnOrderNotFound("RETURN ORDER NOT FOUND");
		}
		returnOrder.get().setStatus("CONFIRMED");
		log.info("Req ID: {} is confirmed", reqId);
		ProcessingCharge processingCharge = chargeRepository
				.findByItemName(returnOrder.get().getComponentType().toUpperCase());
		returnOrder.get().setDeliveryDate(LocalDate.now().plusDays(processingCharge.getProcessingDays()));
		processRequestRepository.save(returnOrder.get());

		return new ReturnConfirmResponse(true);
	}
}
