package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(NameValidException.class);

	public void nameException() {

		logger.error("Must have at least one uppercase character \n" + "Must have used lowercase  character");
	}
}
