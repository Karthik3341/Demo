package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(QuantityValidException.class);

	public void quantityException() {

		logger.error("Quantity must greater  than 0 should be allowed ");
	}
}
