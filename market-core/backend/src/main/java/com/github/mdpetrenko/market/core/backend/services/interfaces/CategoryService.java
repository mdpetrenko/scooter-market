package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.backend.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Optional<Category> findByIdWithProducts(Long id);

    Optional<Category> findByTitle(String title);

}
