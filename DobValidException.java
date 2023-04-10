package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DobValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DobValidException.class);

	public void dobException() {

		logger.error("You are not eligible because age is less than 18 should not be allowed");
	}
}
