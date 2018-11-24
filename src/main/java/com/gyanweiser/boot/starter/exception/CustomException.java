package com.gyanweiser.boot.starter.exception;

import org.springframework.http.HttpStatus;

/**
 * This class handles all the custom exceptions.
 * 
 * @author Siddharth Kar
 */

public class CustomException extends RuntimeException {

	/**
	 * default serialID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * custom exception message
	 */
	private final String message;
	
	/**
	 * custom httpStatus
	 */
	private final HttpStatus httpStatus;

	public CustomException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
