package com.currencyconverter.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.ExchangeRates;
import com.currencyconverter.domain.MonetaryValueDTO;
import com.currencyconverter.exception.ExternalServiceAccessException;
import com.currencyconverter.infrastructure.impl.ExchangeRatesFetcherImpl;
import com.currencyconverter.service.CurrencyConverterService;

/**
 * Service responsible for parsing HTML web pages from its URL.
 */
@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
	
	private static final Logger LOGGER = Logger.getLogger(CurrencyConverterServiceImpl.class.getName());
	private static final String LOG_MESSAGE = "Error fetching exchange rates";

	private ExchangeRatesFetcherImpl exchangeRatesFetcher;

	@Autowired
	public CurrencyConverterServiceImpl(ExchangeRatesFetcherImpl exchangeRatesFetcher) {
		super();
		this.exchangeRatesFetcher = exchangeRatesFetcher;
	}

	@Override
	public MonetaryValueDTO convert(CurrencyEnum sourceCurrency, CurrencyEnum targetCurrency, BigDecimal amount) throws ExternalServiceAccessException {
		MonetaryValueDTO result = null;
		try {
			//Fetches exchange rates
			ExchangeRates er = exchangeRatesFetcher.fetch(sourceCurrency);
			//Converts and instantiates resulting value
			result = new MonetaryValueDTO(targetCurrency, er.convertTo(targetCurrency, amount));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, LOG_MESSAGE, e);
			throw new ExternalServiceAccessException(LOG_MESSAGE, e);
		}
		//Calculate value in the target currency
		return result;
	}
	
}
