package com.gyanweiser.boot.starter.exception;

/**
 * This class handles resource not found exception.
 * 
 * @author Siddharth Kar
 */

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Default serialID
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
