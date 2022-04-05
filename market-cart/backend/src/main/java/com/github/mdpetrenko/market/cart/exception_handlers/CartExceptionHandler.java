package com.github.mdpetrenko.market.cart.exception_handlers;

import com.github.mdpetrenko.market.cart.exceptions.CartError;
import com.github.mdpetrenko.market.cart.exceptions.CartGenerationException;
import com.github.mdpetrenko.market.cart.exceptions.CartNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CartExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchCartNotFoundException(CartNotFoundException e) {
        return new ResponseEntity<>(new CartError(CartError.CartErrors.CART_NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<?> catchCartGenerationException(CartGenerationException e) {
        return new ResponseEntity<>(new CartError(CartError.CartErrors.CART_KEY_IS_NULL.name(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
