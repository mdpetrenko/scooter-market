package com.github.mdpetrenko.market.core.api.exceptions.erors;

import com.github.mdpetrenko.market.api.exceptions.AppError;

public class CoreError extends AppError {
    public enum CoreErrors {
        PRODUCT_NOT_FOUND, ORDER_NOT_FOUND, CATEGORY_NOT_FOUND, INVALID_DATA
    }

    public CoreError(String code, String message) {
        super(code, message);
    }
}
