package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Product;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import com.github.mdpetrenko.market.services.interfaces.ProductService;
import com.github.mdpetrenko.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    @Override
    public void addItem(Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id=" + productId + " does not exists"));
        cart.add(product);
    }

    @Override
    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    @Override
    public void decrementItem(Long productId) {
        cart.decrement(productId);
    }

    @Override
    public Cart getCartForCurrentUser() {
        return cart;
    }

    @Override
    public void clearCart() {
        cart.clear();
    }
}
