package com.github.mdpetrenko.market.core.converters;

import com.github.mdpetrenko.market.core.dtos.OrderDto;
import com.github.mdpetrenko.market.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter implements Converter<OrderDto, Order> {
    private final OrderItemConverter orderItemMapper;

    @Override
    public OrderDto entityToDto(Order entity) {
        OrderDto out = new OrderDto();
        out.setId(entity.getId());
        out.setDeliveryAddress(entity.getDeliveryAddress());
        out.setOwnerName(entity.getOwnerName());
        out.setOwnerEmail(entity.getOwnerEmail());
        out.setOwnerPhone(entity.getOwnerPhone());
        out.setPrice(entity.getPrice());
        out.setItems(entity.getItems().stream().map(orderItemMapper::entityToDto).collect(Collectors.toList()));
        return out;
    }

    @Override
    public Order dtoToEntity(OrderDto dto) {
        throw new UnsupportedOperationException();
    }
}
