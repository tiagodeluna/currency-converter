package com.currencyconverter.infrastructure;

import java.io.IOException;

import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.ExchangeRates;

/**
 * Defines an object responsible for fetching the latest currency rates.
 */
@FunctionalInterface
public interface ExchangeRatesFetcher {

	/**
	 * Fetch all exchange rates based on the currency provided.
	 * @param baseCurrency The reference currency
	 * @return An object containing the rates
	 * @throws IOException 
	 */
	public ExchangeRates fetch(CurrencyEnum baseCurrency) throws IOException;
}
