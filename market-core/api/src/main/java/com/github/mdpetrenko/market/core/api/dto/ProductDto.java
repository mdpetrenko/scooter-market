package com.github.mdpetrenko.market.core.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Product schema")
public class ProductDto {
    @Schema(description = "Product ID", required = true, example = "1")
    private Long id;
    @Schema(description = "Product title", required = true, example = "Scooter")
    private String title;
    @Schema(description = "Product category", required = true)
    private String categoryTitle;
    @Schema(description = "Product price", required = true, example = "100.95")
    private BigDecimal price;

    public ProductDto(Long id, String title, String categoryTitle, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.categoryTitle = categoryTitle;
        this.price = price;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
