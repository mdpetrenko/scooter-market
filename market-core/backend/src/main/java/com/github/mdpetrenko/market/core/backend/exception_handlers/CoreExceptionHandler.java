package com.github.mdpetrenko.market.core.backend.exception_handlers;

import com.github.mdpetrenko.market.api.exceptions.AppError;
import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.*;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CoreExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchProductNotFoundException(ProductNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new CoreError(CoreError.CoreErrors.PRODUCT_NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<?> catchOrderNotFoundException(OrderNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new CoreError(CoreError.CoreErrors.ORDER_NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<?> catchCategoryNotFoundException(CategoryNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new CoreError(CoreError.CoreErrors.CATEGORY_NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchDataValidationException(DataValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError("INVALID_INPUT_DATA", e.getMessage()), HttpStatus.BAD_REQUEST);
    }



}
