package com.inditex.price_service.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.inditex.price_service.AbstractPriceServiceTest;
import com.inditex.price_service.controller.PriceController.PriceResponse;
import com.inditex.price_service.exception.PriceNotFoundException;
import com.inditex.price_service.service.PriceService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class PriceControllerTest extends AbstractPriceServiceTest {

	@Test
	public void getPrice_currentDateNotFound_throwException() {
		
		PriceService priceService = mock(PriceService.class);
		when(priceService.getPrice(anyLong(), anyLong(), any()))
			.thenReturn(Optional.empty());
		
		PriceController priceController = new PriceController(priceService);
		
		assertThrows(PriceNotFoundException.class, () -> priceController.getPrice(BRAND_ID, PRODUCT_ID, null));
		ArgumentCaptor<Long> brandIdCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<Long> productIdCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<LocalDateTime> dateCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
		verify(priceService, times(1)).getPrice(brandIdCaptor.capture(), productIdCaptor.capture(), dateCaptor.capture());
		assertEquals(BRAND_ID, brandIdCaptor.getValue());
		assertEquals(PRODUCT_ID, productIdCaptor.getValue());
		assertNotNull(dateCaptor.getValue());		
	}
	
	@Test
	public void getPrice_found_returnIt() {
		
		LocalDateTime date = LocalDateTime.now();
		PriceService priceService = mock(PriceService.class);
		when(priceService.getPrice(BRAND_ID, PRODUCT_ID, date))
		.thenReturn(Optional.of(getPrice()));
	
		PriceController priceController = new PriceController(priceService);
		PriceResponse priceResponse = priceController.getPrice(BRAND_ID, PRODUCT_ID, date);
		
		assertEquals(BRAND_ID, priceResponse.brandId());
		assertEquals(START_DATE, priceResponse.startDate());
		assertEquals(END_DATE, priceResponse.endDate());
		assertEquals(PRICE_LIST, priceResponse.priceList());
		assertEquals(PRODUCT_ID, priceResponse.productId());
		assertEquals(PRICE, priceResponse.price());
		assertEquals(CURR, priceResponse.currency());
		verify(priceService, times(1)).getPrice(BRAND_ID, PRODUCT_ID, date);
	}
	
}
