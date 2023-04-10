package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AddressValidException.class);

	public void addressException() {
		logger.error(
				"Must have at least one numeric character\nMust have at least one lowercase character\nMust have at least one uppercase character");
	}
}
