package com.github.mdpetrenko.market.core.api.dto;

import java.util.UUID;

public class OrderDetailsDto {
    private UUID guestCartUuid;
    private String ownerName;
    private String deliveryAddress;
    private String ownerPhone;
    private String ownerEmail;

    public OrderDetailsDto() {
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
}
