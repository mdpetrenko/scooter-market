package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.backend.entities.Product;

public class ProductConverter{
    public ProductDto entityToDto(Product entity) {
        return new ProductDto(entity.getId(), entity.getTitle(), entity.getCategory().getTitle(), entity.getPrice());
    }

}
