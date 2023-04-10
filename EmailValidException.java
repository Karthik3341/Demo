package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(EmailValidException.class);

	public void emailException() {

		logger.error("Must have used only lowercase character \n" + "Must have used special symbol among @");
	}
}
