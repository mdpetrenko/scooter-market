package com.github.mdpetrenko.market.core.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Schema(description = "Order schema")
public class OrderDto {
    @Schema(description = "Order id", required = true, example = "1")
    private Long id;
    private DeliveryAddressDto deliveryAddress;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;
    @Schema(description = "Order items list")
    private Collection<OrderItemDto> items;
    @Schema(description = "Order total price")
    private BigDecimal price;
    private String status;

    public OrderDto(Long id, String ownerName, String ownerPhone, String ownerEmail, DeliveryAddressDto deliveryAddress, Collection<OrderItemDto> items, BigDecimal price, String status) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.items = items;
        this.price = price;
        this.status = status;
    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryAddressDto getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressDto deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
