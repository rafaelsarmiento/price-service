package com.inditex.price_service.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inditex.price_service.entity.Price;
import com.inditex.price_service.repository.PriceRepository;
import com.inditex.price_service.service.PriceService;

@Service
public class PersistedPriceServiceImpl implements PriceService {

	private final PriceRepository priceRepository;
	
	public PersistedPriceServiceImpl(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Optional<Price> getPrice(long brandId, long productId, LocalDateTime date) {
		return priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, 
				productId, 
				date, 
				date);
	}

}
