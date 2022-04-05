package com.github.mdpetrenko.market.cart.dto;

import java.math.BigDecimal;
import java.util.Collection;

public class CartDto {
    private Collection<CartItemDto> items;
    private BigDecimal totalPrice;
    private String id;

    public CartDto() {
    }

    public CartDto(Collection<CartItemDto> items, BigDecimal totalPrice, String id) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.id = id;
    }

    public Collection<CartItemDto> getItems() {
        return items;
    }

    public void setItems(Collection<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
