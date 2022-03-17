package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.backend.converters.ProductConverter;
import com.github.mdpetrenko.market.core.backend.entities.Category;
import com.github.mdpetrenko.market.core.backend.entities.Product;
import com.github.mdpetrenko.market.core.backend.services.interfaces.CategoryService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex,
                                    @RequestParam(defaultValue = "3", name = "s") int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize < 1) {
            pageSize = 3;
        }
        return productService.findAll(pageIndex - 1, pageSize).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(productConverter::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found")));
        return productConverter.entityToDto(product);
    }
}
