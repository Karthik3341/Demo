package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PasswordValidException.class);

	public void passException() {

		logger.error("Must have at least one numeric character \n" + "Must have at least one lowercase character \n"
				+ "Must have at least one uppercase character \n"
				+ "Must have at least one special symbol among @#$!%^&*?>< \n"
				+ "Password length should be between 8 and 20");
	}
}
