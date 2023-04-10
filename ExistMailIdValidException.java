package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistMailIdValidException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExistMailIdValidException.class);

	public void existMailException() {
		logger.error("Mail id already exist!!!");
	}
}
