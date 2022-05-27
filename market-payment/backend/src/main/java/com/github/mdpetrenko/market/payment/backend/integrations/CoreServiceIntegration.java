package com.github.mdpetrenko.market.payment.backend.integrations;

import com.github.mdpetrenko.market.core.api.commons.OrderStatus;
import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient webClient;

    public OrderDto findOrderById(String username, Long orderId) {
        return webClient.get()
                .uri("/api/v1/orders/" + orderId)
                .header(username)
                .retrieve()
                //TODO: handle errors
                .bodyToMono(OrderDto.class)
                .block();
    }

    public void changeOrderStatus(String username, Long orderId, OrderStatus orderStatus) {
        webClient.put()
                .uri("/api/v1/orders/" + orderId)
                .bodyValue(orderStatus)
                .header(username)
                .retrieve()
                //TODO: handle errors
                .toBodilessEntity()
                .block();
    }
}
