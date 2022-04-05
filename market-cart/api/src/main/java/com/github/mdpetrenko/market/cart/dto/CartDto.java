package com.github.mdpetrenko.market.cart.dto;

import java.util.Collection;

public class CartDto {
    private Collection<CartItemDto> items;
    private int totalPrice;
    private String id;

    public CartDto() {
    }

    public CartDto(Collection<CartItemDto> items, int totalPrice, String id) {
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
