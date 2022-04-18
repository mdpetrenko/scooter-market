package com.github.mdpetrenko.market.core.backend.converters;

import com.github.mdpetrenko.market.core.api.dto.CategoryDto;
import com.github.mdpetrenko.market.core.backend.entities.Category;
import com.github.mdpetrenko.market.core.backend.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryService categoryService;

    public CategoryDto entityToDto(Category entity) {
        return new CategoryDto(entity.getId(), entity.getTitle().toString());
    }

    public Category dtoToEntity(CategoryDto dto) {
        return categoryService.findById(dto.getId());
    }
}
