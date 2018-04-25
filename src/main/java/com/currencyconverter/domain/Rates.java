package com.currencyconverter.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains the rates in different currencies.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
	
	@JsonProperty("JPY")
	private BigDecimal jpy;
	@JsonProperty("USD")
	private BigDecimal usd;
	@JsonProperty("EUR")
	private BigDecimal eur;

	public Rates() {
		super();
	}
	
	/**
	 * Returns the exchange rate of the reported currency.
	 * @param currency The currency you want to get the conversion rate
	 * @return The rate from the currency provided
	 */
	public BigDecimal getByCurrency(CurrencyEnum currency) {
		switch (currency) {
		case EUR:
			return defaultValue(eur); 
		case JPY:
			return defaultValue(jpy);
		case USD:
			return defaultValue(usd);
		default:
			return null;
		}
	}
	
	private BigDecimal defaultValue(BigDecimal value) {
		//Since the Fixer API doesn't provide the rate for the base currency
		// (by definition, equal to 1) this code returns the reference value
		return value != null ? value : BigDecimal.ONE;
	}
	
	public void setJpy(BigDecimal jpy) {
		this.jpy = jpy;
	}

	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	}

	public void setEur(BigDecimal eur) {
		this.eur = eur;
	}

}
