package com.gyanweiser.boot.starter.dto;

import lombok.Builder;
import lombok.Data;

/**
 * This class defines the signup response.
 * 
 * @author Siddharth Kar
 */

@Data
@Builder
public class SignupResponse {

	/**
	 * this stores the request processing status
	 */
	private String signupStatus;

	/**
	 * this stores the generic message
	 */
	private String message;
	
	/**
	 * this stores the username of the registered user
	 */
	private String username;

}
