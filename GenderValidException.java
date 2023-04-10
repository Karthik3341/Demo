package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenderValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GenderValidException.class);

	public void genderException() {

		logger.error(
				"Must have used lowercase character \n" + "\t\t\t or \t\t\t \n" + "Must have used uppercase character");
	}
}
