package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.model.Product;

import java.util.List;

public interface CartService {

    List<Product> getCartContent();

    void addProductById(Long id);

    void removeProductById(Long id);

}
