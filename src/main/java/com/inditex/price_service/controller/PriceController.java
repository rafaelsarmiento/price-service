package com.inditex.price_service.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.price_service.entity.Price;
import com.inditex.price_service.exception.PriceNotFoundException;
import com.inditex.price_service.service.PriceService;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

	private final Logger log = LoggerFactory.getLogger(PriceController.class);
	
	private final PriceService priceService;
	
	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}

	@GetMapping("/price/{brandId}/{productId}")
	public PriceResponse getPrice(@PathVariable long brandId, @PathVariable long productId, @RequestParam(required = false) LocalDateTime date) {
		
		log.debug("Getting the price for the brand [{}] and the product [{}] at the date [{}]", brandId, productId, date);
		
		LocalDateTime dateOrNow = Optional.ofNullable(date)
				.orElse(LocalDateTime.now(ZoneOffset.UTC));
		
		return priceService.getPrice(brandId, productId, dateOrNow)
				.map(PriceResponse::fromPrice)
				.orElseThrow(() -> new PriceNotFoundException(brandId, productId, dateOrNow));
	}
	
	record PriceResponse(long brandId, long productId, int priceList, LocalDateTime startDate, LocalDateTime endDate,
			BigDecimal price, String currency) {
		
		private static PriceResponse fromPrice(Price price) {
			return new PriceResponse(price.getBrandId(), price.getProductId(), price.getPriceList(), price.getStartDate(), 
					price.getEndDate(), price.getPrice(), price.getCurr());
		}
		
	}	
}
