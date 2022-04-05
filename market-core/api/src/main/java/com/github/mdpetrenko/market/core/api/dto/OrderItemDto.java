package com.github.mdpetrenko.market.core.api.dto;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerItem;
    private BigDecimal price;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String productTitle, int quantity, BigDecimal pricePerItem, BigDecimal price) {
        this.id = id;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
