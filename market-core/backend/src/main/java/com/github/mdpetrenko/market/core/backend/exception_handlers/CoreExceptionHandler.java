package com.github.mdpetrenko.market.core.backend.exception_handlers;

import com.github.mdpetrenko.market.core.api.exceptions.CategoryNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.DataValidationException;
import com.github.mdpetrenko.market.core.api.exceptions.OrderNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.ProductNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import com.github.mdpetrenko.market.core.api.exceptions.erors.FieldsValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return new ResponseEntity<>(new FieldsValidationError(e.getErrorMessages()), HttpStatus.BAD_REQUEST);
    }

}
