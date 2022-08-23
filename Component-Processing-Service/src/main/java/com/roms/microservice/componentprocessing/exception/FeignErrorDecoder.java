package com.roms.microservice.componentprocessing.exception;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                log.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 401:
            {
                return new TokenInvalidException("Invalid Token");
            }
            case 500:
            {
            	System.out.println("SNF");
            	return new ServiceNotFoundException("SERVICE_NOT_FOUND");
            }
            default:
                return new Exception(response.reason());
        }
    }
}
