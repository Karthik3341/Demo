package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistDonorIdValidException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExistDonorIdValidException.class);

	public void existMailException() {
		logger.error("Donor Id already exist!!!");
	}
}
