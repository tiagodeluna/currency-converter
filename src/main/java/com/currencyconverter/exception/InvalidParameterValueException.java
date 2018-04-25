package com.currencyconverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "One or more parameters are invalid")
public class InvalidParameterValueException extends Exception {

    static final long serialVersionUID = -3387516993224229948L;

    public InvalidParameterValueException() {
		super();
	}
    
}
