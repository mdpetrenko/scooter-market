package com.github.mdpetrenko.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private String ownerName;
    private String deliveryAddress;
    private String ownerPhone;
    private String ownerEmail;

}
