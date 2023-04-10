package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloodBankIdValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BloodBankIdValidException.class);

	public void bidException() {

		logger.error("Blood Bank Id must greater than 1100 should be allowed");
	}
}
