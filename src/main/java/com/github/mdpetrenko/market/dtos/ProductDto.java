package com.github.mdpetrenko.market.dtos;

import com.github.mdpetrenko.market.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String categoryTitle;
    private float price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.categoryTitle = product.getCategory().getTitle();
        this.price = product.getPrice();
    }
}
