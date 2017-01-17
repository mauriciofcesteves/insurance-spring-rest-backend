package com.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Initializes REST services.
 * 
 * @author Mauricio Esteves
 */
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	 
	public static void main(String[] args) {
		log.info("Running application...");
		SpringApplication.run(Application.class, args);
	}
}