package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.utils.Cart;

public interface CartService {

    void addItem(Long productId);
    void removeItem(Long productId);
    void decrementItem(Long productId);
    Cart getCartForCurrentUser();
    void clearCart();

}
