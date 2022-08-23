package com.roms.microservice.componentprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "VALIDATION FAILED")
public class ConstraintsInvalidException extends Exception {
    public ConstraintsInvalidException(String msg) {
        super(msg);
    }
}
