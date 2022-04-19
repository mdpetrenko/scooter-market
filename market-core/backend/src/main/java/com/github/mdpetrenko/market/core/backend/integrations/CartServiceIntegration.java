package com.github.mdpetrenko.market.core.backend.integrations;

import com.github.mdpetrenko.market.cart.dto.CartDto;
import com.github.mdpetrenko.market.cart.exceptions.errors.CartError;
import com.github.mdpetrenko.market.core.api.exceptions.CartServiceIntegrationException;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient webClient;

    public CartDto getCartForCurrentUser(String username, UUID guestCartUuid) {
        return webClient.get()
                .uri("/api/v1/cart/" + guestCartUuid)
                .header("username", username)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        getResponseError()
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clearCart(String username, UUID guestCartUuid) {
        webClient.get()
                .uri("/api/v1/cart/" + guestCartUuid + "/clear")
                .header("username", username)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        getResponseError()
                )
                .toBodilessEntity()
                .block();
    }

    private Function<ClientResponse, Mono<? extends Throwable>> getResponseError() {
        return clientResponse -> clientResponse.bodyToMono(CoreError.class).map(
                body -> {
                    if (body.getCode().equals(CartError.CartErrors.CART_NOT_FOUND.name())) {
                        return new CartServiceIntegrationException("Incorrect request: cart not found");
                    }
                    if (body.getCode().equals(CartError.CartErrors.CART_KEY_IS_NULL.name())) {
                        return new CartServiceIntegrationException("Incorrect request: cart key is null");
                    }
                    if (body.getCode().equals(CartError.CartErrors.SERVICE_NOT_AVAILABLE.name())) {
                        return new CartServiceIntegrationException("Cart Service is not available");
                    }
                    return new CartServiceIntegrationException("Unknown integration error");
                }
        );
    }

}
