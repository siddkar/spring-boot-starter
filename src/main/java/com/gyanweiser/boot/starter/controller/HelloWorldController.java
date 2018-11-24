package com.gyanweiser.boot.starter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the Hello World Controller class.
 * 
 * @author Siddharth Kar
 */

@RestController
public class HelloWorldController {

	/**
	 * This method responds to the end-point /hello.
	 * @return ResponseEntity<String>
	 */
	@GetMapping(path = "/hello")
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<>("Hello!!! Welcome to Spring Boot Starter App", HttpStatus.OK);
	}
}
