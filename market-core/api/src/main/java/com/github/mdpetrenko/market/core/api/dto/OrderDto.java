package com.github.mdpetrenko.market.core.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Collection;

@Schema(description = "Order schema")
public class OrderDto {
    @Schema(description = "Order id", required = true, example = "1")
    private Long id;
    @Schema(description = "Order items list")
    private Collection<OrderItemDto> items;
    @Schema(description = "Order total price")
    private BigDecimal price;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;
    private String deliveryAddress;

    public OrderDto(Long id, Collection<OrderItemDto> items, BigDecimal price, String ownerName, String ownerPhone, String ownerEmail, String deliveryAddress) {
        this.id = id;
        this.items = items;
        this.price = price;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.deliveryAddress = deliveryAddress;
    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
