package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.backend.entities.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Integer pageNumber, Integer pageSize, Integer minPrice, Integer maxPrice, String titlePart);
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
