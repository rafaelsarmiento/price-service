package com.inditex.price_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inditex.price_service.entity.Price;

public class AbstractPriceServiceTest {

	protected static final long PRICE_ID = 1L;
	protected static final long BRAND_ID = 2L;
	protected static final long PRODUCT_ID = 3L;
	protected static final LocalDateTime START_DATE = LocalDateTime.now();
	protected static final LocalDateTime END_DATE = LocalDateTime.now().plusHours(1);
	protected static final int PRICE_LIST = 4;
	protected static final int PRIORITY = 5;
	protected static final BigDecimal PRICE = BigDecimal.TEN;
	protected static final String CURR = "EUR";
	
	protected static Price getPrice() {
		return new Price(PRICE_ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR);
	}
	
}
