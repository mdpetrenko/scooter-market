package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.model.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);

    Optional<Category> findByIdWithProducts(Long id);

    Optional<Category> findByTitle(String title);

}
