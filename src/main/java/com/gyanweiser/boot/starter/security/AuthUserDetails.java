package com.gyanweiser.boot.starter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gyanweiser.boot.starter.entities.AppUser;
import com.gyanweiser.boot.starter.repository.AppUserRepository;

/**
 * This is a customized class which provides strategy to load user-specific
 * data. It provides customized implementation of {@link UserDetailsService}.
 * 
 * @author Siddharth Kar
 */

@Service("authUserDetails")
public class AuthUserDetails implements UserDetailsService {

	@Autowired
	AppUserRepository appUserRepository;

	/**
	 * This method loads UserDetails for a username.
	 * @param username {@link String}
	 * @return userDetails {@link UserDetails}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		AppUser user = appUserRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found.");
		}
		return User.withUsername(username).password(user.getPassword()).authorities(user.getRoles())
				.accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
	}

}
