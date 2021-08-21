package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.model.Category;
import com.github.mdpetrenko.market.repositories.CategoryRepository;
import com.github.mdpetrenko.market.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByIdWithProducts(Long id) {
        return categoryRepository.findByIdWithProducts(id);
    }

    @Override
    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
