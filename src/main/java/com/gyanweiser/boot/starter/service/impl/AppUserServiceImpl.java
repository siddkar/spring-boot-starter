package com.gyanweiser.boot.starter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gyanweiser.boot.starter.dto.LoginRequest;
import com.gyanweiser.boot.starter.dto.LoginResponse;
import com.gyanweiser.boot.starter.dto.SignupResponse;
import com.gyanweiser.boot.starter.entities.AppUser;
import com.gyanweiser.boot.starter.exception.CustomException;
import com.gyanweiser.boot.starter.repository.AppUserRepository;
import com.gyanweiser.boot.starter.security.JwtTokenProvider;
import com.gyanweiser.boot.starter.service.AppUserService;
import com.gyanweiser.boot.starter.utils.AppConstants;

/**
 * This class is the implementation of AppUser services.
 * 
 * @author Siddharth Kar
 */

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/**
	 * This method registers an AppUser to the system.
	 * 
	 * @param AppUser
	 *            user
	 * @return a {@link SignupResponse} containing request processing info
	 */
	public SignupResponse signup(AppUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		appUserRepository.save(user);
		return SignupResponse
				.builder()
				.signupStatus(AppConstants.SIGNUP_STATUS)
				.message(AppConstants.SIGNUP_MESSAGE)
				.username(user.getUsername())
				.build();
	}

	/**
	 * This method helps an existing user to signin.
	 * 
	 * @param loginRequest
	 *            {@link LoginRequest}
	 * @return a valid token {@link LoginResponse}
	 */
	@Override
	public LoginResponse signin(LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setAccessToken(
					jwtTokenProvider.createToken(username, appUserRepository.findByUsername(username).getRoles()));
			loginResponse.setTokenType("Bearer");
			return loginResponse;
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password!!!", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
