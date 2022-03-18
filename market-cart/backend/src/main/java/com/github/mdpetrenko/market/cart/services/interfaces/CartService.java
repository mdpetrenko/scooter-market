package com.github.mdpetrenko.market.cart.services.interfaces;

import com.github.mdpetrenko.market.cart.entities.Cart;

import java.util.UUID;

public interface CartService {
    Cart getCartForCurrentUser(String username, UUID uuid);

    void addItem(String username, UUID uuid, Long productId);

    void removeItem(String username, UUID uuid, Long productId);

    void decrementItem(String username, UUID uuid, Long productId);

    void clearCart(String username, UUID uuid);

    void merge(String username, UUID cartId);
}
