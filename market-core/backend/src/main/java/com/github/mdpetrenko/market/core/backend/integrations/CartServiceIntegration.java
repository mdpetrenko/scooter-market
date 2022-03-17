package com.github.mdpetrenko.market.core.backend.integrations;

import com.github.mdpetrenko.market.cart.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient webClient;

    public CartDto getCartForCurrentUser(String username, UUID guestCartUuid) {
        return webClient.get()
                .uri("/api/v1/cart/" + guestCartUuid)
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clearCart(String username, CartDto cartDto) {
        webClient.get()
                .uri("/api/v1/cart/" + cartDto.getId())
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
