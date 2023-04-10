package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockNoValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StockNoValidException.class);

	public void stockNoException() {

		logger.error("Stock number must greater  than 2500 should be allowed");
	}
}
