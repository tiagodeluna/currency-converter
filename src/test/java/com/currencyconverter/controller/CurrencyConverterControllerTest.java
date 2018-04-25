package com.currencyconverter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.currencyconverter.domain.CurrencyDTO;
import com.currencyconverter.domain.CurrencyEnum;
import com.currencyconverter.domain.MonetaryValueDTO;
import com.currencyconverter.exception.EqualSourceAndTargetParametersException;
import com.currencyconverter.exception.ExternalServiceAccessException;
import com.currencyconverter.exception.InvalidParameterValueException;
import com.currencyconverter.service.impl.CurrencyConverterServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyConverterControllerTest {

	@InjectMocks
	private CurrencyConverterController controller;
	
	@Mock
	private CurrencyConverterServiceImpl service;
	
	//Test data
	private CurrencyEnum source = CurrencyEnum.EUR;
	private CurrencyEnum target = CurrencyEnum.USD;
	private BigDecimal amount = new BigDecimal(100);

	@Test
	public void testConvert_OK() throws Exception {
		MonetaryValueDTO expected = new MonetaryValueDTO(target, new BigDecimal(118.18));
		
		//Method calling
		when(service.convert(source, target, amount)).thenReturn(expected);
		MonetaryValueDTO actual = controller.convert(source, target, amount);

		//Assertions
		assertNotNull(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConvert_SourceCurrencyNull() throws Exception {
		source = null;
		
		try {
			//Method calling
			controller.convert(source, target, amount);
			
			fail("Should have thrown InvalidParameterValueException");
		} catch (InvalidParameterValueException e) {
			//Good!
		}
	}

	@Test
	public void testConvert_TargetCurrencyNull() throws Exception {
		target = null;
		
		try {
			//Method calling
			controller.convert(source, target, amount);
			
			fail("Should have thrown InvalidParameterValueException");
		} catch (InvalidParameterValueException e) {
			//Good!
		}
	}

	@Test
	public void testConvert_AmountNull() throws Exception {
		amount = null;
		
		try {
			//Method calling
			controller.convert(source, target, amount);
			
			fail("Should have thrown InvalidParameterValueException");
		} catch (InvalidParameterValueException e) {
			//Good!
		}
	}
	
	@Test
	public void testConvert_NegativeAmount() throws Exception {
		amount = new BigDecimal(-1);
		
		try {
			//Method calling
			controller.convert(source, target, amount);
			
			fail("Should have thrown InvalidParameterValueException");
		} catch (InvalidParameterValueException e) {
			//Good!
		}
	}
	
	@Test
	public void testConvert_ThrowEqualSourceAndTargetParametersException() throws Exception {
		target = source;
		
		try {
			//Method calling
			controller.convert(source, target, amount);
			
			fail("Should have thrown EqualSourceAndTargetParametersException");
		} catch (EqualSourceAndTargetParametersException e) {
			//Good!
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testConvert_ThrowExternalServiceAccessException() throws Exception {
		try {
			//Method calling
			when(service.convert(source, target, amount)).thenThrow(ExternalServiceAccessException.class);
			controller.convert(source, target, amount);
			
			fail("Should have thrown ExternalServiceAccessException");
		} catch (ExternalServiceAccessException e) {
			//Good!
		}
	}

	@Test
	public void testGetCurrencies_OK() {
		//Method calling
		List<CurrencyDTO> currencies = controller.getCurrencies();
		
		//Assertions
		assertNotNull(currencies);
		assertEquals(CurrencyEnum.values().length, currencies.size());
	}
	
}
