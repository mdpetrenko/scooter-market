package com.github.mdpetrenko.market.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.cart-service")
@Data
public class CoreIntegrationProperties {
    private String url;
    private int connectionTimeout;
    private int readTimeout;
    private int writeTimeout;

    }


