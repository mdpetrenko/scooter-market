package com.github.mdpetrenko.market.core.backend.exceptions;

import com.github.mdpetrenko.market.api.exceptions.MarketError;
import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MarketExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> resourceNotFoundExceptionCaught(ResourceNotFoundException e) {
        return new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> illegalArgumentExceptionCaught(IllegalArgumentException e) {
        return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
