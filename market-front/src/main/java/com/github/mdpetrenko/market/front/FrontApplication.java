package com.github.mdpetrenko.market.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * Front service
 * AngularJS + Bootstrap are used in frontend
 * All requests to backend are sent to gateway using standard HTTP methods
 */

@SpringBootApplication
public class FrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontApplication.class, args);
	}

}
