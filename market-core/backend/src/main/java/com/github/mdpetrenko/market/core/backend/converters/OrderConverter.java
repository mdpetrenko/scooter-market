package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.DeliveryAddressDto;
import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import com.github.mdpetrenko.market.core.api.dto.OrderItemDto;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import com.github.mdpetrenko.market.core.backend.integrations.CartServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;
    private final DeliveryAddressConverter deliveryAddressConverter;

    public OrderDto entityToDto(Order order) {
        DeliveryAddressDto deliveryAddressDto = deliveryAddressConverter.entityToDto(order.getDeliveryAddress());
        Collection<OrderItemDto> orderItems = order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
        return new OrderDto(
                order.getId(), order.getOwnerName(), order.getOwnerPhone(), order.getOwnerEmail(), deliveryAddressDto,
                orderItems, order.getPrice(), order.getStatus().name()
        );
    }
}
