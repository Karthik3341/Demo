package com.chainsys.bloodsourcespring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNameValidExceptoin extends Exception {

	
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserNameValidExceptoin.class);

	public void userNameException() {

		logger.error("Must have at least one uppercase character \n Must have at least one lowercase character \n Must have at least one numeric character ");
	}
}
