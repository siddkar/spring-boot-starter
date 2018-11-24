package com.gyanweiser.boot.starter.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * This class helps in providing customized implementation for
 * {@link WebSecurity} to create the {@link FilterChainProxy} known as the
 * Spring Security Filter Chain. The springSecurityFilterChain is the
 * {@link Filter} that the {@link DelegatingFilterProxy} delegates to.
 * 
 * @author Siddharth Kar
 *
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/**
	 * This method allows configuring web based security for specific http requests,
	 * because by default it is applied to all the requests.
	 * 
	 * @param http
	 *            {@link HttpSecurity}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();

		// No session will be created or used by spring security as JWT is used.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Configuring entry points
		http.authorizeRequests().antMatchers("/users/signin").permitAll().antMatchers("/users/signup").permitAll()
				.antMatchers("/h2-console/**/**").permitAll().anyRequest().authenticated();

		// Exception Handler URL
		http.exceptionHandling().accessDeniedPage("/error/403");

		// Apply JWT filter
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * This method creates a bean of {@link BCryptPasswordEncoder}
	 * 
	 * @return a {@link BCryptPasswordEncoder} instance
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
