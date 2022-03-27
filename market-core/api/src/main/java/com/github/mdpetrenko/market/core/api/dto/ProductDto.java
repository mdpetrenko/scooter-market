package com.github.mdpetrenko.market.core.api.dto;

public class ProductDto {
    private Long id;
    private String title;
    private String categoryTitle;
    private int price;

    public ProductDto(Long id, String title, String categoryTitle, int price) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
