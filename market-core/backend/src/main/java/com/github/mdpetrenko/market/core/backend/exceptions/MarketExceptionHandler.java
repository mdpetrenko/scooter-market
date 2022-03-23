package com.github.mdpetrenko.market.core.backend.exceptions;

import com.github.mdpetrenko.market.api.exceptions.MarketError;
import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class MarketExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundExceptionCaught(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchDataValidationExceptions(DataValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new FieldsValidationError(e.getErrorMessages()), HttpStatus.BAD_REQUEST);
    }



}
