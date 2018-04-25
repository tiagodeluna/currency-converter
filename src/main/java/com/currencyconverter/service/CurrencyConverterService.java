package com.currencyconverter.service;

import java.math.BigDecimal;

import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.MonetaryValueDTO;
import com.currencyconverter.exception.ExternalServiceAccessException;

/**
 * Defines a service responsible for converting a object into another.
 */
@FunctionalInterface
public interface CurrencyConverterService {

	/**
	 * Receives a path and conducts some analysis of the corresponding file. 
	 * @param sourceCurrency The original currency of amount
	 * @param targetCurrency The currency to which the value is to be converted
	 * @param amount The value to be converted
	 * @return An object containing the value converted
	 * @throws ExternalServiceAccessException 
	 */
	MonetaryValueDTO convert(CurrencyEnum sourceCurrency, CurrencyEnum targetCurrency, BigDecimal amount)
			throws ExternalServiceAccessException;
}
