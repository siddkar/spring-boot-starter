package com.gyanweiser.boot.starter.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.gyanweiser.boot.starter.exception.CustomException;

/**
 * This class is a filter which authenticates the requests. It extends
 * {@link GenericFilterBean} and provides implementation for doFilter method.
 * 
 * @author Siddharth Kar
 */

public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	/**
	 * This method authenticates the requests and on successful authentication calls
	 * the next filter in line.
	 * @param request {@link ServletRequest}
	 * @param response {@link ServletResponse}
	 * @param filterChain {@link FilterChain}
	 * @throws IOException, {@link ServletException}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				// holder for Authentication object in the context
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (CustomException e) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendError(e.getHttpStatus().value(), e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

}
