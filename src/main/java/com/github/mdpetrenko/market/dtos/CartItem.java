package com.github.mdpetrenko.market.dtos;

import com.github.mdpetrenko.market.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private String categoryTitle;
    private int quantity;
    private int pricePerItem;
    private int totalPrice;

    public CartItem(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.categoryTitle = product.getCategory().getTitle();
        this.pricePerItem = product.getPrice();
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        totalPrice = pricePerItem * quantity;
    }

}
