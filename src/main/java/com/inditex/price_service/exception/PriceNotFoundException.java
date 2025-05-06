package com.inditex.price_service.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -837599740763334476L;

	public PriceNotFoundException(long brandId, long productId, LocalDateTime date) {
		super(String.format("Price not found for the brand [%d] and the product [%d] at the date [%s]", brandId, productId, date));
	}
}
