package com.github.mdpetrenko.market.core.services.interfaces;

import com.github.mdpetrenko.market.core.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long id);

    Optional<Category> findByIdWithProducts(Long id);

    Optional<Category> findByTitle(String title);

}
