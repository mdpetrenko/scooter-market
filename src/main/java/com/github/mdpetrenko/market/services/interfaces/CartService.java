package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.model.Cart;

import java.security.Principal;
import java.util.UUID;

public interface CartService {
    Cart getCartForCurrentUser(Principal principal, UUID uuid);

    void addItem(Principal principal, UUID uuid, Long productId);

    void removeItem(Principal principal, UUID uuid, Long productId);

    void decrementItem(Principal principal, UUID uuid, Long productId);

    void clearCart(Principal principal, UUID uuid);

    void clearCart(Cart cart);

    void merge(Principal principal, UUID cartId);
}
