package com.github.mdpetrenko.market.core.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.model.Cart;
import com.github.mdpetrenko.market.core.repositories.CartRepository;
import com.github.mdpetrenko.market.core.services.interfaces.CartService;
import com.github.mdpetrenko.market.core.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final String CART_PREFIX = "ScooterMarket_Cart_";
    private final ProductService productService;
    private final CartRepository cartRepository;

    @Override
    public Cart getCartForCurrentUser(String username, UUID uuid) {
        String key = getCartKey(username, uuid);
        return cartRepository.findById(key).orElseGet(() -> new Cart(key));
    }

    private String getCartKey(String username, UUID uuid) {
        if (username == null && uuid == null) {
            throw new IllegalArgumentException("Not Enough information for generate key");
        }
        if (username != null) {
            return CART_PREFIX + username;
        }
        return CART_PREFIX + uuid;
    }

    @Override
    public void addItem(String username, UUID uuid, Long productId) {
        Cart cart = getCartForCurrentUser(username, uuid);
        if (!cart.add(productId)) {
            cart.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found. Id: " + productId)));
        }
        cartRepository.save(cart);
    }

    @Override
    public void removeItem(String username, UUID uuid, Long productId) {
        cartRepository.save(getCartForCurrentUser(username, uuid).remove(productId));
    }

    @Override
    public void decrementItem(String username, UUID uuid, Long productId) {
        cartRepository.save(getCartForCurrentUser(username, uuid).decrement(productId));
    }

    @Override
    public void clearCart(String username, UUID uuid) {
        cartRepository.save(getCartForCurrentUser(username, uuid));
    }

    @Override
    public void clearCart(Cart cart) {
        cartRepository.save(cart.clear());
    }

    @Override
    public void merge(String username, UUID cartId) {
        Cart userCart = getCartForCurrentUser(username, null);
        Cart guestCart = getCartForCurrentUser(null, cartId);
        cartRepository.save(userCart.merge(guestCart));
        cartRepository.save(guestCart);
    }
}
