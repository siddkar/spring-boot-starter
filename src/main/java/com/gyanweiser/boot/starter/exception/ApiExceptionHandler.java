package com.gyanweiser.boot.starter.exception;

import java.time.LocalDateTime;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is the controller advice config class.
 * 
 * @author Siddharth Kar
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Build the response for Exception.
	 * <p>
	 * The implementation that creates the response object.
	 * 
	 * @param apiError
	 *            the model object
	 * @return a {@code ResponseEntity} instance
	 */
	private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse, HttpStatus status) {
		return new ResponseEntity<>(exceptionResponse, status);
	}

	/**
	 * Customize the response for EntityNotFoundException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, final HttpServletRequest request) {
		return buildResponseEntity(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getRequestURI()),
				HttpStatus.NOT_FOUND);
	}

	/**
	 * Customize the response for EntityExistsException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex the exception
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(EntityExistsException.class)
	protected ResponseEntity<Object> handleEntityExists(EntityExistsException ex, final HttpServletRequest request) {
		return buildResponseEntity(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getRequestURI()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	/**
	 * This method handles generic exceptions.
	 * 
	 * @param exception
	 * @param request
	 * @return a ResponseEntity instance containing the error response
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException ex, final HttpServletRequest request) {
		return buildResponseEntity(
				new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getRequestURI()),
				HttpStatus.NOT_FOUND);
	}
	
	/**
	 * This method handles custom exceptions.
	 * 
	 * @param exception
	 * @param request
	 * @return a ResponseEntity instance containing the error response
	 */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(final CustomException ex, final HttpServletRequest request) {
		return buildResponseEntity(
				new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getRequestURI()),
				ex.getHttpStatus());
	}

	/**
	 * This method handles generic exceptions.
	 * 
	 * @param exception
	 * @param request
	 * @return a ResponseEntity instance containing the error response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(final Exception ex, final HttpServletRequest request) {
		return buildResponseEntity(
				new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getRequestURI()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
