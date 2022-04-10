package com.github.mdpetrenko.market.core.backend.configurations;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PayPalConfig {
    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String secret;

    private PayPalEnvironment environment;

    @PostConstruct
    private void init() {
        this.environment = new PayPalEnvironment.Sandbox(clientId, secret);
    }

    @Bean
    public PayPalHttpClient payPalClient() {
        return new PayPalHttpClient(environment);
    }
}
