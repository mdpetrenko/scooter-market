package com.github.mdpetrenko.market.core.backend.validators;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.api.exceptions.DataValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Product price is below zero");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Product title is blank");
        }
        if (!errors.isEmpty()) {
            throw new DataValidationException(errors);
        }
    }
}
