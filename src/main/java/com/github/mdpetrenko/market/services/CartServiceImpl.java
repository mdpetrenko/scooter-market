package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Product;
import com.github.mdpetrenko.market.repositories.Cart;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import com.github.mdpetrenko.market.services.interfaces.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final Cart cart;
    private final ProductService productService;

    @Override
    public Map<Product, Integer> getAllProducts() {
        return cart.getProducts();
    }

    @Override
    @Transactional
    public void addProductById(Long id) {
        cart.addProduct(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id=" + id + " not fond")));
    }

    @Override
    @Transactional
    public void removeProductById(Long id) {
        cart.removeProduct(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id=" + id + " not fond")));
    }
}
