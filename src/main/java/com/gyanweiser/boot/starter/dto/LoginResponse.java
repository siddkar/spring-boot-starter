package com.gyanweiser.boot.starter.dto;

import lombok.Data;

/**
 * This class defines the response for signin request
 * 
 * @author Siddharth Kar
 */

@Data	
public class LoginResponse {
	/**
	 * stores the accessToken
	 */
	private String accessToken;

	/**
	 * stores the tokenType
	 */
	private String tokenType;

}
