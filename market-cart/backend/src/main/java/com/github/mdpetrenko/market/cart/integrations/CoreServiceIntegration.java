package com.github.mdpetrenko.market.cart.integrations;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integrations.core.url}")
    private String coreServiceUrl;

    public Optional<ProductDto> findById(Long productId) {
        return Optional.ofNullable(restTemplate.getForObject(coreServiceUrl + "/api/v1/products/" + productId, ProductDto.class));
    }

}
