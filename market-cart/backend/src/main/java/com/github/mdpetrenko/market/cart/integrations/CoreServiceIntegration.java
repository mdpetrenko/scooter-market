package com.github.mdpetrenko.market.cart.integrations;

import com.github.mdpetrenko.market.cart.exceptions.CoreServiceIntegrationException;
import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;

    public ProductDto findProductById(Long productId) {
        return coreServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(CoreError.class).map(
                                body ->
                                {
                                    if (body.getCode().equals(CoreError.CoreErrors.PRODUCT_NOT_FOUND.name())) {
                                        return new CoreServiceIntegrationException("Bad data: product not found");
                                    }
                                    return new CoreServiceIntegrationException("Unknown error");
                                }
                        )
                )
                .bodyToMono(ProductDto.class)
                .block();
    }
}
