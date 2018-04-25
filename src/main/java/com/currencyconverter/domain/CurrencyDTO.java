package com.currencyconverter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {

	private String id;
	private String name;
	private String symbol;
	
	public CurrencyDTO(CurrencyEnum currency) {
		this.id = currency.toString();
		this.name = currency.getName();
		this.symbol = currency.getSymbol();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
