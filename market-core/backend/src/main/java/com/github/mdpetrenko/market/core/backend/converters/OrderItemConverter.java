package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.OrderItemDto;
import com.github.mdpetrenko.market.core.backend.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem entity) {
        return new OrderItemDto(entity.getId(), entity.getProduct().getTitle(), entity.getQuantity(), entity.getPricePerItem(), entity.getPrice());
    }

}
