package com.gyanweiser.boot.starter.entities;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is an enum of Roles.
 * 
 * @author Siddharth Kar
 */

public enum Role implements GrantedAuthority {
	@JsonProperty("ROLE_ADMIN")
	ROLE_ADMIN,
	@JsonProperty("ROLE_CLIENT")
	ROLE_CLIENT;

	@Override
	public String getAuthority() {
		return name();
	}
}
