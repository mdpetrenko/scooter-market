package com.github.mdpetrenko.market.cart.converters;

import com.github.mdpetrenko.market.cart.CartItemDto;
import com.github.mdpetrenko.market.cart.entities.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getProductId(), cartItem.getProductTitle(), cartItem.getQuantity(), cartItem.getPricePerItem(), cartItem.getPrice());
    }
}
