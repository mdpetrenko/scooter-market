package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Cart;
import com.github.mdpetrenko.market.repositories.CartRepository;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import com.github.mdpetrenko.market.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final String CART_PREFIX = "ScooterMarket_Cart_";
    private final ProductService productService;
    private final CartRepository cartRepository;

    @Override
    public Cart getCartForCurrentUser(Principal principal, UUID uuid) {
        String key = getCartKey(principal, uuid);
        return cartRepository.findById(key).orElseGet(() -> new Cart(key));
    }

    private String getCartKey(Principal principal, UUID uuid) {
        if (principal == null && uuid == null) {
            throw new IllegalArgumentException("Not Enough information for generate key");
        }
        if (principal != null) {
            return CART_PREFIX + principal.getName();
        }
        return CART_PREFIX + uuid;
    }

    @Override
    public void addItem(Principal principal, UUID uuid, Long productId) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        if (!cart.add(productId)) {
            cart.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found. Id: " + productId)));
        }
        cartRepository.save(cart);
    }

    @Override
    public void removeItem(Principal principal, UUID uuid, Long productId) {
        cartRepository.save(getCartForCurrentUser(principal, uuid).remove(productId));
    }

    @Override
    public void decrementItem(Principal principal, UUID uuid, Long productId) {
        cartRepository.save(getCartForCurrentUser(principal, uuid).decrement(productId));
    }

    @Override
    public void clearCart(Principal principal, UUID uuid) {
        cartRepository.save(getCartForCurrentUser(principal, uuid));
    }

    @Override
    public void clearCart(Cart cart) {
        cartRepository.save(cart.clear());
    }

    @Override
    public void merge(Principal principal, UUID cartId) {
        Cart userCart = getCartForCurrentUser(principal, null);
        Cart guestCart = getCartForCurrentUser(null, cartId);
        cartRepository.save(userCart.merge(guestCart));
        cartRepository.save(guestCart);
    }
}
