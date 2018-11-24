package com.gyanweiser.boot.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyanweiser.boot.starter.dto.LoginRequest;
import com.gyanweiser.boot.starter.dto.LoginResponse;
import com.gyanweiser.boot.starter.dto.SignupResponse;
import com.gyanweiser.boot.starter.entities.AppUser;
import com.gyanweiser.boot.starter.service.AppUserService;


/**
 * This is a controller class which handles /user/* requests
 * 
 * @author Siddharth Kar
 */

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AppUserService appUserService;

	/**
	 * This controller method handles user sign-up request
	 * @param user {@link AppUser}
	 * @return a {@link ResponseEntity} containing the newly registered {@link AppUser}
	 */
	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SignupResponse> signup(@RequestBody AppUser user) {
		return new ResponseEntity<>(appUserService.signup(user), HttpStatus.CREATED);
	}
	
	/**
	 * This controller method handles the user sign-in request.
	 * @param loginRequest {@link LoginRequest}
	 * @return a {@link ResponseEntity} containing the {@link LoginResponse}
	 */
	@PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) {
		return new ResponseEntity<>(appUserService.signin(loginRequest), HttpStatus.OK);
	}
}
