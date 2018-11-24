package com.gyanweiser.boot.starter.service;

import com.gyanweiser.boot.starter.dto.LoginRequest;
import com.gyanweiser.boot.starter.dto.LoginResponse;
import com.gyanweiser.boot.starter.dto.SignupResponse;
import com.gyanweiser.boot.starter.entities.AppUser;

/**
 * This interface is the contract for implementation of AppUser services.
 * 
 * @author Siddharth Kar
 */

public interface AppUserService {
	
	SignupResponse signup(AppUser user);
	
	LoginResponse signin(LoginRequest loginRequest);
}
