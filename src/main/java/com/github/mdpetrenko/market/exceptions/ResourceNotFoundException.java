package com.github.mdpetrenko.market.exceptions;

import lombok.NoArgsConstructor;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
