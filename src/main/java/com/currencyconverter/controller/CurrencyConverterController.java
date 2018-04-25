package com.currencyconverter.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconverter.domain.CurrencyDTO;
import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.MonetaryValueDTO;
import com.currencyconverter.exception.EqualSourceAndTargetParametersException;
import com.currencyconverter.exception.ExternalServiceAccessException;
import com.currencyconverter.exception.InvalidParameterValueException;
import com.currencyconverter.service.CurrencyConverterService;

@RestController
@RequestMapping("api")
public class CurrencyConverterController {

	private static final Logger LOGGER = Logger.getLogger(CurrencyConverterController.class.getName());
	
	private CurrencyConverterService currencyConverterService;
	
	@Autowired
	public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
		super();
		this.currencyConverterService = currencyConverterService;
	}

	/**
	 * Receives an amount in a given currency and converts it to another currency.
	 * @param source The original currency of amount
	 * @param target The currency to which the value is to be converted
	 * @param amount The value to be converted
	 * @return An object containing the value converted
	 * @throws InvalidParameterValueException 
	 * @throws ExternalServiceAccessException 
	 * @throws EqualSourceAndTargetParametersException 
	 */
	@GetMapping
	public MonetaryValueDTO convert(CurrencyEnum source, CurrencyEnum target, BigDecimal amount)
			throws ExternalServiceAccessException, InvalidParameterValueException, EqualSourceAndTargetParametersException {
		
		LOGGER.log(Level.INFO, "Converting monetary value");
		validateEntry(source, target, amount);
		return currencyConverterService.convert(source, target, amount);
	}
	
	/**
	 * Maps the {@link CurrencyEnum} type to a list of {@link CurrencyDTO}. 
	 * @return The list of currencies available.
	 */
	@GetMapping(path="currencies")
	public List<CurrencyDTO> getCurrencies() {

		LOGGER.log(Level.INFO, "Getting currencies");
		return Arrays.stream(CurrencyEnum.values())
				.map(currency -> new CurrencyDTO(currency))
				.collect(Collectors.toList());
	}

	private void validateEntry(CurrencyEnum source, CurrencyEnum target, BigDecimal amount)
			throws InvalidParameterValueException, EqualSourceAndTargetParametersException {
		if (source == null) {
			LOGGER.log(Level.WARNING, "Source Currency provided is null");
			throw new InvalidParameterValueException();
		}
		if (target == null) {
			LOGGER.log(Level.WARNING, "Target Currency provided is null");
			throw new InvalidParameterValueException();
		}
		if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
			LOGGER.log(Level.WARNING, "Amount provided is null");
			throw new InvalidParameterValueException();
		}
		if (source == target) {
			LOGGER.log(Level.WARNING, "Both source and target currencies are the same");
			throw new EqualSourceAndTargetParametersException();
		}
	}

}
