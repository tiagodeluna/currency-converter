package com.currencyconverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to access external service")
public class ExternalServiceAccessException extends Exception {

	private static final long serialVersionUID = 7742081196320225654L;

	public ExternalServiceAccessException(String message) {
        super(message);
    }

	public ExternalServiceAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
