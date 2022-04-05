package com.github.mdpetrenko.market.core.api.exceptions;

import java.util.List;

public class DataValidationException extends RuntimeException {
    private List<String> errorMessages;

    public DataValidationException(List<String> messages) {
        this.errorMessages = messages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
