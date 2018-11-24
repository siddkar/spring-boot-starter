package com.gyanweiser.boot.starter.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * This class handles the exception responses.
 * 
 * @author Siddharth Kar
 *
 */

public class ExceptionResponse {

	// Timestamp of the exception
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	// user understandable error message
	private String errorMessage;

	// requested uri
	private String requestedURI;
	
	public ExceptionResponse() {
	}

	public ExceptionResponse(LocalDateTime timestamp, String errorMessage, String requestedURI) {
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.requestedURI = requestedURI;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getRequestedURI() {
		return requestedURI;
	}

}
