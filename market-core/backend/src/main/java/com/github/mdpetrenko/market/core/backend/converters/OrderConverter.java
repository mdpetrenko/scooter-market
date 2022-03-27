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

    public OrderDto entityToDto(Order entity) {
        OrderDto out = new OrderDto();
        out.setId(entity.getId());
        out.setDeliveryAddress(entity.getDeliveryAddress());
        out.setOwnerName(entity.getOwnerName());
        out.setOwnerEmail(entity.getOwnerEmail());
        out.setOwnerPhone(entity.getOwnerPhone());
        out.setPrice(entity.getPrice());
        out.setItems(entity.getItems().stream().map(orderItemMapper::orderItemToDto).collect(Collectors.toList()));
        return out;
    }
}
