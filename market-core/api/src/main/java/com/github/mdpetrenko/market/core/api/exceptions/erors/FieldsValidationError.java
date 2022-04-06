package com.github.mdpetrenko.market.core.api.exceptions.erors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldsValidationError {
    private List<String> errorList;
}
