package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.model.Product;

import java.util.Map;

public interface CartService {

    Map<Product, Integer> getAllProducts();

    void addProductById(Long id);

    void removeProductById(Long id);

}
