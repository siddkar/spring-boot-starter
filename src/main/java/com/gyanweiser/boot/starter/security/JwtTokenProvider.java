package com.gyanweiser.boot.starter.security;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gyanweiser.boot.starter.entities.Role;
import com.gyanweiser.boot.starter.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class takes care of creating a token, verifying a token and extracting
 * details from a token.
 * 
 * @author Siddharth Kar
 */

@Component
public class JwtTokenProvider {

	@Value("${jwt.token.secret-key}")
	private String secretKey;

	@Value("${jwt.token.expire-length}")
	private long validityInMilliseconds;

	@Autowired
	private AuthUserDetails authUserDetails;

	/**
	 * This method is a post bean creation init method it takes care of encoding the
	 * secret key
	 */
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	/**
	 * @param username
	 *            {@link String}
	 * @param roles
	 *            {@link Role}
	 * @return a signed JWT token {@link String}
	 */
	public String createToken(String username, Set<Role> roles) {

		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	/**
	 * This method parses the token and returns the username from the payload.
	 * 
	 * @param token
	 *            {@link String}
	 * @return username {@link String}
	 */
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * This method authenticates the token and returns the entity/user.
	 * 
	 * @param token
	 *            {@link String}
	 * @return an entity/user {@link Authentication}
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = authUserDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	/**
	 * This method extracts the bearer token from the request.
	 * 
	 * @param request
	 *            {@link String}
	 * @return the token {@link String}
	 */
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	/**
	 * This method validates the token.
	 * 
	 * @param token
	 *            {@link String}
	 * @return true or false {@link Boolean}
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException ex) {
			throw new CustomException("Expired or Invalid Token!!!", HttpStatus.UNAUTHORIZED);
		}
	}
}
