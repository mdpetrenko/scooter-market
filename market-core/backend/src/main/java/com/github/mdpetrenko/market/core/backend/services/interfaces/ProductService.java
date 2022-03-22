package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.backend.entities.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(int page, int size);
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}