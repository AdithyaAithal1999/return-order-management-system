package com.roms.microservice.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "INVALID TOKEN")
public class TokenInvalidException extends Exception {
    public TokenInvalidException(String msg) {
        super(msg);
    }
}
