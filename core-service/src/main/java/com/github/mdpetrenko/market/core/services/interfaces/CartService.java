package com.github.mdpetrenko.market.core.services.interfaces;

import com.github.mdpetrenko.market.core.model.Cart;

import java.security.Principal;
import java.util.UUID;

public interface CartService {
    Cart getCartForCurrentUser(String username, UUID uuid);

    void addItem(String username, UUID uuid, Long productId);

    void removeItem(String username, UUID uuid, Long productId);

    void decrementItem(String username, UUID uuid, Long productId);

    void clearCart(String username, UUID uuid);

    void clearCart(Cart cart);

    void merge(String username, UUID cartId);
}
