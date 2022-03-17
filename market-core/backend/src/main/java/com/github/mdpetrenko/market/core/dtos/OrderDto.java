package com.github.mdpetrenko.market.core.dtos;

import com.github.mdpetrenko.market.api.dto.OrderItemDto;
import lombok.Data;

import java.util.Collection;

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
