package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.core.backend.repositories.ProductRepository;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import com.github.mdpetrenko.market.core.backend.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Integer page, Integer size, Integer minPrice, Integer maxPrice, String titlePart) {

        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
