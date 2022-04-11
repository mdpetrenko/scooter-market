package com.github.mdpetrenko.market.core.api.dto;

import java.util.UUID;

public class OrderDetailsDto {
    private UUID guestCartUuid;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;
    private DeliveryAddressDto deliveryAddress;

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(UUID guestCartUuid, String ownerName, String ownerPhone, String ownerEmail, DeliveryAddressDto deliveryAddress) {
        this.guestCartUuid = guestCartUuid;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.deliveryAddress = deliveryAddress;
    }

    public UUID getGuestCartUuid() {
        return guestCartUuid;
    }

    public void setGuestCartUuid(UUID guestCartUuid) {
        this.guestCartUuid = guestCartUuid;
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

    public DeliveryAddressDto getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressDto deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
