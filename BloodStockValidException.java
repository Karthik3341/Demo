package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloodStockValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BloodStockValidException.class);

	public void bStockException() {

		logger.error("Blood Stock must greater  than 0 should be allowed");
	}

}
