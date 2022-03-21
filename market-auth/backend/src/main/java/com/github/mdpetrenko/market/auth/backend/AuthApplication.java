package com.github.mdpetrenko.market.auth.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * Authentication service for market
 * Takes jwt-token from frontend service, converts it to username and appends it to headers for further requests
 *
 */

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
