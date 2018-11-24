package com.gyanweiser.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the main entry class.
 * 
 * @author Siddharth Kar
 */

@SpringBootApplication(scanBasePackages = "com.cg.boot.todo")
public class TodoApp {

	/**
	 * This is the main method which starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TodoApp.class, args);
	}
}
