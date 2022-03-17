package com.github.mdpetrenko.market.cart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CartAppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
