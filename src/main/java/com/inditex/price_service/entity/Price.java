package com.inditex.price_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "PRICES")
public class Price {

	@Id
	private Long id;
	@Column
	private long brandId;
	@Column
	private LocalDateTime startDate;
	@Column
	private LocalDateTime endDate;
	@Column
	private int priceList;
	@Column
	private long productId;
	@Column
	private int priority;
	@Column
	private BigDecimal price;
	@Column
	private String curr;
	
	public Price() {
		
	}

	public Long getId() {
		return id;
	}

	public long getBrandId() {
		return brandId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public int getPriceList() {
		return priceList;
	}

	public long getProductId() {
		return productId;
	}

	public int getPriority() {
		return priority;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCurr() {
		return curr;
	}
	
}
