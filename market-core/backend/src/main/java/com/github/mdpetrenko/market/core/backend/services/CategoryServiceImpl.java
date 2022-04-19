package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.backend.entities.Category;
import com.github.mdpetrenko.market.core.backend.repositories.CategoryRepository;
import com.github.mdpetrenko.market.core.backend.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found. Id=" + id));
    }
}
