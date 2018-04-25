package com.currencyconverter.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Object containing exchange rates in different currencies against a base currency 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {

	private CurrencyEnum base;
	private Rates rates;
	
	public ExchangeRates() {
		super();
	}
	
	/**
	 * Business method responsible for converting the amount from the base 
	 * currency to the target currency.
	 * @param currency The target currency
	 * @param amount The value to be converted
	 * @return The converted value
	 */
	public BigDecimal convertTo(CurrencyEnum currency, BigDecimal amount) {
		BigDecimal rate = rates.getByCurrency(currency);
		return amount.multiply(rate).setScale(2, RoundingMode.HALF_EVEN);
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public CurrencyEnum getBase() {
		return base;
	}

	public void setBase(CurrencyEnum base) {
		this.base = base;
	}

}