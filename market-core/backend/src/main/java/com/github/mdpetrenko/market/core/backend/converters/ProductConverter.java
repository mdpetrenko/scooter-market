package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.backend.entities.Product;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryConverter categoryConverter;

    public ProductDto entityToDto(Product entity) {
        return new ProductDto(entity.getId(), entity.getTitle(), categoryConverter.entityToDto(entity.getCategory()), entity.getPrice());
    }

    public Product dtoToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        product.setCategory(categoryConverter.dtoToEntity(dto.getCategory()));
        return product;
    }

}
