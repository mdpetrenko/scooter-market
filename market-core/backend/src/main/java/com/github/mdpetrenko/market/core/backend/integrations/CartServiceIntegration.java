package com.github.mdpetrenko.market.core.backend.integrations;

import com.github.mdpetrenko.market.cart.dto.CartDto;
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

    public void clearCart(String username, UUID guestCartUuid) {
        webClient.get()
                .uri("/api/v1/cart/" + guestCartUuid + "/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
