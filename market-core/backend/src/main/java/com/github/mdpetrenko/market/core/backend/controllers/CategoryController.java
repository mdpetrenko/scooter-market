package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.core.api.dto.CategoryDto;
import com.github.mdpetrenko.market.core.api.exceptions.CategoryNotFoundException;
import com.github.mdpetrenko.market.core.backend.entities.Category;
import com.github.mdpetrenko.market.core.backend.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<String> getCategoryTitles() {
        return categoryService.findAll().stream().map(Category::getTitle).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id).map(c -> new CategoryDto(c.getId(), c.getTitle()))
                .orElseThrow(() -> new CategoryNotFoundException("Category id = " + id + " not found"));
    }
}
