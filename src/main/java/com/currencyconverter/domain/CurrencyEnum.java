package com.currencyconverter.domain;

/**
 * Definition of the currencies available for conversion.
 */
public enum CurrencyEnum {
	EUR("Euro", "€"),
	USD("US Dollar", "$"),
	JPY("Japanese Yen", "¥");
	
	private String name;
	private String symbol;

	private CurrencyEnum(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

}
