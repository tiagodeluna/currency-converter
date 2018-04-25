package com.currencyconverter.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object that encapsulates a value in a specific currency.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonetaryValueDTO {

	private CurrencyDTO currency;
	private BigDecimal value;
	
	public MonetaryValueDTO() {
		super();
	}

	public MonetaryValueDTO(CurrencyEnum currency, BigDecimal value) {
		super();
		this.currency = new CurrencyDTO(currency);
		this.value = value;
	}

	@JsonProperty("symbol")
	public String getSymbol() {
		//The currency symbol is transfered via Json by this property
		if (this.currency != null) {
			return this.currency.getSymbol();
		}
		
		return null;
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public CurrencyDTO getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDTO currency) {
		this.currency = currency;
	}

}
