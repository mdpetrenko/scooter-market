package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.backend.repositories.ProductRepository;
import com.github.mdpetrenko.market.core.backend.repositories.specifications.ProductSpecification;
import com.github.mdpetrenko.market.core.backend.services.interfaces.CategoryService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import com.github.mdpetrenko.market.core.backend.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Integer page, Integer size, Integer minPrice, Integer maxPrice, String titlePart) {
        Specification<Product> spec = getProductSpecification(minPrice, maxPrice, titlePart);
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found. Id: " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Product updateProduct(ProductDto productDto) {
        Product product = findById(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findById(productDto.getCategory().getId()));
        return save(product);
    }

    private Specification<Product> getProductSpecification(Integer minPrice, Integer maxPrice, String titlePart) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecification.titleLike(titlePart));
        }
        return spec;
    }
}
