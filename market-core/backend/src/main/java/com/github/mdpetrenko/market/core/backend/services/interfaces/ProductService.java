package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.backend.entities.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(Integer pageNumber, Integer pageSize, Integer minPrice, Integer maxPrice, String titlePart);
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);

    Product updateProduct(ProductDto productDto);
}
