package com.currencyconverter.infrastructure.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.ExchangeRates;
import com.currencyconverter.infrastructure.ExchangeRatesFetcher;

@Component
@PropertySource("classpath:application.properties")
public class ExchangeRatesFetcherImpl implements ExchangeRatesFetcher {

	//Read value from properties file using Spring
	@Value("${fixer.api.url}")
	private String urlApi;
	
	private RestTemplate restTemplate;

	public ExchangeRatesFetcherImpl() {
		super();
		this.restTemplate = new RestTemplate();
	}

	@Override
	public ExchangeRates fetch(CurrencyEnum baseCurrency) throws IOException {
		//Mounts URL string to access the service
		String url = String.format(urlApi, baseCurrency.toString());
		//Fetch latest exchange rates
		return restTemplate.getForObject(url, ExchangeRates.class);
	}

}
