package com.gyanweiser.boot.starter.dto;

import lombok.Data;

/**
 * This class defines the data contract for signin request
 * 
 * @author Siddharth Kar
 */

@Data
public class LoginRequest {

	/**
	 * maps to the username in the request
	 */
	private String username;

	/**
	 * maps to the password in the request
	 */
	private String password;
}
