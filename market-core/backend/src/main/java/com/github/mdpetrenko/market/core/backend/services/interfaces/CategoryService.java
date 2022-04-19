package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.backend.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

}
