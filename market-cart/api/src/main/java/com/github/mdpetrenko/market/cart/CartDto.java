package com.github.mdpetrenko.market.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDto {
    private Collection<CartItemDto> items;
    private int totalPrice;
    private String id;
}
