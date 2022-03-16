package com.github.mdpetrenko.market.core.converters;

import com.github.mdpetrenko.market.api.dto.OrderItemDto;
import com.github.mdpetrenko.market.core.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter implements Converter<OrderItemDto, OrderItem> {

    @Override
    public OrderItemDto entityToDto(OrderItem entity) {
        return new OrderItemDto(entity.getId(), entity.getProduct().getTitle(), entity.getQuantity(), entity.getPricePerItem(), entity.getPrice());
    }

    @Override
    public OrderItem dtoToEntity(OrderItemDto dto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
