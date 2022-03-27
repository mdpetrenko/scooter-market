package com.github.mdpetrenko.market.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/***
 * Cart service
 * Works with user carts. User can put products to cart regardless he logged or not
 * Reddis is used as cart storage
 * Interacts with Product service
 */

@SpringBootApplication
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}
