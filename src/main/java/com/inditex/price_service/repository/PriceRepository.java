package com.inditex.price_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inditex.price_service.entity.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long>{

}
