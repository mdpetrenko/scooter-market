package com.github.mdpetrenko.market.cart.integration;

import com.github.mdpetrenko.market.api.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoreIntegration {
    private final RestTemplate restTemplate;

    @Value("${integrations.core.url}")
    private String coreUrl;

    public Optional<ProductDto> findById(Long productId) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:5555/core/api/v1/products/" + productId, ProductDto.class));
    }

}
