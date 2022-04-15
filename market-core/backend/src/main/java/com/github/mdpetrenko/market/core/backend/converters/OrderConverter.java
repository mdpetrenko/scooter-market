package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.ShippingAddressDto;
import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import com.github.mdpetrenko.market.core.api.dto.OrderItemDto;
import com.github.mdpetrenko.market.core.backend.entities.Order;
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
        ShippingAddressDto shippingAddressDto = deliveryAddressConverter.entityToDto(order.getShippingAddress());
        Collection<OrderItemDto> orderItems = order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
        return new OrderDto(
                order.getId(), order.getOwnerName(), order.getOwnerPhone(), order.getOwnerEmail(), shippingAddressDto,
                orderItems, order.getPrice(), order.getStatus().name()
        );
    }
}
