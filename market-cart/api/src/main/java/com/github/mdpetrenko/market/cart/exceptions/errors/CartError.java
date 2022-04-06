package com.github.mdpetrenko.market.cart.exceptions.errors;

import com.github.mdpetrenko.market.api.exceptions.AppError;

public class CartError extends AppError {
    public CartError(String code, String message) {
        super(code, message);
    }

    public CartError() {
    }

    public enum CartErrors {
        CART_NOT_FOUND, CART_KEY_IS_NULL, CORE_INTEGRATION_ERROR
    }
}
