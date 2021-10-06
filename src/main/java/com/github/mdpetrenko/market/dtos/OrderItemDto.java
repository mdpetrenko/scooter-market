package com.github.mdpetrenko.market.dtos;

import com.github.mdpetrenko.market.model.OrderItem;
import com.github.mdpetrenko.market.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerItem;
    private int price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.pricePerItem = product.getPrice();
        this.quantity = 1;
        this.price = product.getPrice();
    }

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerItem = orderItem.getPricePerItem();
        this.price = orderItem.getPrice();
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        price = pricePerItem * quantity;
    }

}
