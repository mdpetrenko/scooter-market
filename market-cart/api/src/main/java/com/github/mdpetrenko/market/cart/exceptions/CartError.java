package com.github.mdpetrenko.market.cart.exceptions;

import com.github.mdpetrenko.market.api.exceptions.AppError;

public class CartError extends AppError {
    public enum CartErrors {
        CART_NOT_FOUND, CART_IS_BROKEN
    }

    public CartError(String code, String message) {
        super(code, message);
    }
}
