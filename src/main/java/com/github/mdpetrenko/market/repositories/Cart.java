package com.github.mdpetrenko.market.repositories;

import com.github.mdpetrenko.market.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
@NoArgsConstructor
public class Cart {

    private final Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        products.putIfAbsent(product, 0);
        products.compute(product, (k, v) -> v = v + 1);
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product) && products.get(product) > 0) {
            products.compute(product, (k, v) -> v = v - 1);
        }
    }

}
