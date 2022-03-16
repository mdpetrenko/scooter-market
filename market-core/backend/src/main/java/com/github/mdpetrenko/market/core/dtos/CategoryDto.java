package com.github.mdpetrenko.market.core.dtos;

import com.github.mdpetrenko.market.core.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
//    private List<ProductDto> products;

}
