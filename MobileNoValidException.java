package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobileNoValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MobileNoValidException.class);

	public void mobileException() {

		logger.error("The mobile number should be of 10 digits");
	}
}
