package com.github.mdpetrenko.market.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private int pricePerItem;
    private int price;

}
