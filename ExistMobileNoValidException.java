package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistMobileNoValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExistMobileNoValidException.class);

	public void existMobileException() {

		logger.error("Mobile number already exist!!! Try with another number");
	}
}
