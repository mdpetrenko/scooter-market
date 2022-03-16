package com.github.mdpetrenko.market.core.converters;

import com.github.mdpetrenko.market.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.entities.Product;

public class ProductConverter implements Converter<ProductDto, Product> {
    @Override
    public ProductDto entityToDto(Product entity) {
        return new ProductDto(entity.getId(), entity.getTitle(), entity.getCategory().getTitle(), entity.getPrice());
    }

    @Override
    public Product dtoToEntity(ProductDto dto) {
        throw new UnsupportedOperationException();
    }
}
