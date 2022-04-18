package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.core.api.dto.CategoryDto;
import com.github.mdpetrenko.market.core.api.exceptions.CategoryNotFoundException;
import com.github.mdpetrenko.market.core.backend.converters.CategoryConverter;
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
    private final CategoryConverter categoryConverter;

    @GetMapping
    public List<CategoryDto> getCategoryTitles() {
        return categoryService.findAll().stream().map(categoryConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return categoryConverter.entityToDto(category);
    }
}
