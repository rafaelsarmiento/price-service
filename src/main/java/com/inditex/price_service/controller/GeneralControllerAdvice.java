package com.inditex.price_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.inditex.price_service.exception.PriceNotFoundException;

@RestControllerAdvice
public class GeneralControllerAdvice {

	private final Logger log = LoggerFactory.getLogger(GeneralControllerAdvice.class);
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(PriceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void priceNotFoundExceptionHandler(PriceNotFoundException exception) {
		log.debug(exception.getMessage());
	}
	
}
