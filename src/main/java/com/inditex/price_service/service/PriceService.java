package com.inditex.price_service.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.price_service.entity.Price;

public interface PriceService {
	
	/**
	 * It gets the price, if it exists, of a product from a brand in a specific date.
	 *
	 * @param brandId	brand ID
	 * @param productId	product ID
	 * @param date		date
	 * @return the price if it exists or empty
	 */
	Optional<Price> getPrice(long brandId, long productId, LocalDateTime date);

}
