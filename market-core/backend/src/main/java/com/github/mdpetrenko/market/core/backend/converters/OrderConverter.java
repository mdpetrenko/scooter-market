package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemMapper;

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDeliveryAddress(order.getDeliveryAddress());
        orderDto.setOwnerName(order.getOwnerName());
        orderDto.setOwnerEmail(order.getOwnerEmail());
        orderDto.setOwnerPhone(order.getOwnerPhone());
        orderDto.setPrice(order.getPrice());
        orderDto.setItems(order.getItems().stream().map(orderItemMapper::orderItemToDto).collect(Collectors.toList()));
        orderDto.setStatus(order.getStatus().name());
        return orderDto;
    }
}
