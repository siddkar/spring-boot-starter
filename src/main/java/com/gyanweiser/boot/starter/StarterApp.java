package com.gyanweiser.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the main entry class.
 * 
 * @author Siddharth Kar
 */

@SpringBootApplication(scanBasePackages = "com.cg.boot.todo")
public class StarterApp {

	/**
	 * This is the main method which starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StarterApp.class, args);
	}
}
