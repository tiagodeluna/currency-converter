package com.currencyconverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Both source and target currencies are the same")
public class EqualSourceAndTargetParametersException extends Exception {

	private static final long serialVersionUID = 6291083925729199378L;

	public EqualSourceAndTargetParametersException() {
		super();
	}
    
}
