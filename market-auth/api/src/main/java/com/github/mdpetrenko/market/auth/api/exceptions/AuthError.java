package com.github.mdpetrenko.market.auth.api.exceptions;

import com.github.mdpetrenko.market.api.exceptions.AppError;

public class AuthError extends AppError {
    public AuthError(String code, String message) {
        super(code, message);
    }

    public AuthError() {
    }

    public enum AuthErrors {
        RESOURCE_NOT_FOUND, AUTH_FAILED
    }
}
