package com.currencyconverter.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.ExchangeRates;
import com.currencyconverter.domain.MonetaryValueDTO;
import com.currencyconverter.domain.Rates;
import com.currencyconverter.exception.ExternalServiceAccessException;
import com.currencyconverter.infrastructure.impl.ExchangeRatesFetcherImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyConverterServiceImplTest {

	@InjectMocks
	private CurrencyConverterServiceImpl service;
	
	@Mock
	private ExchangeRatesFetcherImpl exchangeRatesFetcher;
	
	//Test data
	private CurrencyEnum source = CurrencyEnum.EUR;
	private CurrencyEnum target = CurrencyEnum.USD;
	private BigDecimal amount = new BigDecimal(100);

	@Test
	public void testConvert_OK() throws IOException, ExternalServiceAccessException {
		ExchangeRates exchangeRates = new ExchangeRates();
		exchangeRates.setBase(source);
		Rates rates = new Rates();
		rates.setEur(new BigDecimal(1));
		rates.setUsd(new BigDecimal(1.18));
		rates.setJpy(new BigDecimal(0.13));
		exchangeRates.setRates(rates);

		//Method calling
		when(exchangeRatesFetcher.fetch(source)).thenReturn(exchangeRates);
		MonetaryValueDTO actual = service.convert(source, target, amount);
		
		//Assertions
		assertNotNull(actual);
		assertEquals(target.toString(), actual.getCurrency().getId());
		assertEquals(exchangeRates.convertTo(target, amount), actual.getValue());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testConvert_ThrowIOException() throws IOException {
		try {
			//Method calling
			when(exchangeRatesFetcher.fetch(source)).thenThrow(IOException.class);
			service.convert(source, target, amount);
			
			fail("Should have thrown ExternalServiceAccessException");
		} catch (ExternalServiceAccessException e) {
			//Good!
		}
	}
}
