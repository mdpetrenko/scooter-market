package com.github.mdpetrenko.market.core.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * Core service consist of Product service and Order service
 * Product service is responsible for operations on products (search, add, change, delete etc)
 * Order service is responsible for order operations (create orders) and interacts with Cart service
 */

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
