package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.dtos.CategoryDto;
import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Category;
import com.github.mdpetrenko.market.services.interfaces.CategoryService;
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
    public List<String> findCategoryTitles() {
        return categoryService.findAll().stream().map(Category::getTitle).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id).map(CategoryDto :: new).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found"));
    }
}
