package com.github.mdpetrenko.market.core.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private UUID guestCartUuid;
    private String ownerName;
    private String deliveryAddress;
    private String ownerPhone;
    private String ownerEmail;

}
