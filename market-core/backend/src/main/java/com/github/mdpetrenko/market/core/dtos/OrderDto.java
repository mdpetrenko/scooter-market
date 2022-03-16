package com.github.mdpetrenko.market.core.dtos;

import com.github.mdpetrenko.market.api.dto.OrderItemDto;
import com.github.mdpetrenko.market.core.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Long id;
    private Collection<OrderItemDto> items;
    private int price;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;
    private String deliveryAddress;

}
