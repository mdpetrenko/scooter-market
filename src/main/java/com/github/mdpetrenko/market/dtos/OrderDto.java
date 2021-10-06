package com.github.mdpetrenko.market.dtos;

import com.github.mdpetrenko.market.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Collection<OrderItemDto> items;
    private int price;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;
    private String deliveryAddress;


    public OrderDto(Order order) {
        this.id = order.getId();
        this.items = order.getItems().stream().map(OrderItemDto :: new).collect(Collectors.toList());
        this.price = order.getPrice();
        this.ownerName = order.getOwnerName();
        this.ownerPhone = order.getOwnerPhone();
        this.ownerEmail = order.getOwnerEmail();
        this.deliveryAddress = order.getDeliveryAddress();
    }

}
