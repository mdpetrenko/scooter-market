package com.github.mdpetrenko.market.cart.services;

import com.github.mdpetrenko.market.api.dto.ProductDto;
import com.github.mdpetrenko.market.cart.entities.Cart;
import com.github.mdpetrenko.market.cart.repositories.CartRepository;
import com.github.mdpetrenko.market.cart.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final String CART_PREFIX = "ScooterMarket_Cart_";
    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;

    @Override
    public Cart getCartForCurrentUser(String username, UUID uuid) {
        String key = getCartKey(username, uuid);
        return cartRepository.findById(key).orElseGet(() -> new Cart(key));
    }

    private String getCartKey(String username, UUID uuid) {
        if (username == null && uuid == null) {
            throw new IllegalArgumentException("Not Enough information for generate key");
        }
        return CART_PREFIX + Objects.requireNonNullElse(username, uuid);
    }

    @Override
    public void addItem(String username, UUID uuid, Long productId) {
        Cart cart = getCartForCurrentUser(username, uuid);
        if (!cart.add(productId)) {
            ProductDto productDto = restTemplate.getForObject("http://localhost:5555/core/api/v1/products/" + productId, ProductDto.class);
            assert productDto != null;
            cart.add(productDto);
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