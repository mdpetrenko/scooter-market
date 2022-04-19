package com.github.mdpetrenko.market.auth.backend.exception_handlers;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.auth.api.exceptions.AuthError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j

public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AuthError(AuthError.AuthErrors.RESOURCE_NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchAuthenticationException(BadCredentialsException e) {
        return new ResponseEntity<>(new AuthError(AuthError.AuthErrors.AUTH_FAILED.name(), e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
