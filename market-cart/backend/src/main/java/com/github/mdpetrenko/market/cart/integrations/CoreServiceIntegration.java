package com.github.mdpetrenko.market.cart.integrations;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient webClient;

    public Optional<ProductDto> findProductById(Long productId) {
        return Optional.ofNullable(webClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block());
    }
}
