package com.github.mdpetrenko.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private Long ownerId;
    private String ownerName;
    private String deliveryAddress;
    private String ownerPhone;
    private String ownerEmail;
    private Collection<CartItem> cartItems;

}
