package com.github.mdpetrenko.market.core.api.exceptions.erors;

import com.github.mdpetrenko.market.api.exceptions.AppError;

public class CoreError extends AppError {
    public enum CoreErrors {
        RESOURCE_NOT_FOUND, CART_INTEGRATION_ERROR
    }

    public CoreError() {
    }

    public CoreError(String code, String message) {
        super(code, message);
    }


}
