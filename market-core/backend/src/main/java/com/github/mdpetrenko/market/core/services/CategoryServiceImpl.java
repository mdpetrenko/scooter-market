package com.github.mdpetrenko.market.core.services;

import com.github.mdpetrenko.market.core.entities.Category;
import com.github.mdpetrenko.market.core.repositories.CategoryRepository;
import com.github.mdpetrenko.market.core.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

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
