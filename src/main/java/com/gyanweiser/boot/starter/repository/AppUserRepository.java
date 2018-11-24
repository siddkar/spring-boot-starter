package com.gyanweiser.boot.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyanweiser.boot.starter.entities.AppUser;

/**
 * This interface takes care of handling CRUD operations for the AppUser entity.
 * 
 * @author Siddharth Kar
 */

@Repository("appUserRepository")
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	/**
	 * this method finds a user by username. 
	 * @param username {@link String}
	 * @return user {@link AppUser}
	 */
	AppUser findByUsername(String username);
}
