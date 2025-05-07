package com.inditex.price_service.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.inditex.price_service.AbstractPriceServiceTest;
import com.inditex.price_service.entity.Price;
import com.inditex.price_service.repository.PriceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersistedPriceServiceImplTest extends AbstractPriceServiceTest {
	
	@Test
	public void getPrice_notFound() {
		
		LocalDateTime date = LocalDateTime.now();
		PriceRepository priceRepository = mock(PriceRepository.class);
		when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(BRAND_ID,
				PRODUCT_ID,
				date,
				date))
			.thenReturn(Optional.empty());
		
		PersistedPriceServiceImpl priceService = new PersistedPriceServiceImpl(priceRepository);
		Optional<Price> priceOp = priceService.getPrice(BRAND_ID, PRODUCT_ID, date);
		
		assertTrue(priceOp.isEmpty());
		verify(priceRepository, times(1)).findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(BRAND_ID, 
				PRODUCT_ID, 
				date, 
				date);
	}

	@Test
	public void getPrice_found() {
		
		LocalDateTime date = LocalDateTime.now();
		PriceRepository priceRepository = mock(PriceRepository.class);
		when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(BRAND_ID,
				PRODUCT_ID,
				date,
				date))
			.thenReturn(Optional.of(getPrice()));
		
		PersistedPriceServiceImpl priceService = new PersistedPriceServiceImpl(priceRepository);
		Optional<Price> priceOp = priceService.getPrice(BRAND_ID, PRODUCT_ID, date);
		
		assertTrue(priceOp.isPresent());
		Price price = priceOp.get();
		assertEquals(PRICE_ID, price.getId());
		assertEquals(BRAND_ID, price.getBrandId());
		assertEquals(START_DATE, price.getStartDate());
		assertEquals(END_DATE, price.getEndDate());
		assertEquals(PRICE_LIST, price.getPriceList());
		assertEquals(PRODUCT_ID, price.getProductId());
		assertEquals(PRIORITY, price.getPriority());
		assertEquals(PRICE, price.getPrice());
		assertEquals(CURR, price.getCurr());
		verify(priceRepository, times(1)).findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(BRAND_ID, 
				PRODUCT_ID, 
				date, 
				date);		
	}	
}
