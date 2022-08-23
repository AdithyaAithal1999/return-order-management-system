package com.roms.microservice.componentprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "RETURN ORDER NOT FOUND")
public class ReturnOrderNotFound extends Exception {
    public ReturnOrderNotFound(String msg) {
        super(msg);
    }
}
