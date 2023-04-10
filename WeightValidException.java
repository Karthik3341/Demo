package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WeightValidException.class);

	public void weightException() {

		logger.error("You are not eligible because weight is less than 40 should not be allowed");
	}
}
