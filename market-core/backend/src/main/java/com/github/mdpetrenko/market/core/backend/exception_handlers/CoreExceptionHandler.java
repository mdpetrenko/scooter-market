package com.github.mdpetrenko.market.core.backend.exception_handlers;

import com.github.mdpetrenko.market.api.exceptions.AppError;
import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import com.github.mdpetrenko.market.core.api.exceptions.DataValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CoreExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundExceptionCaught(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new CoreError(HttpStatus.NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchDataValidationExceptions(DataValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError("INVALID_INPUT_DATA", e.getMessage()), HttpStatus.BAD_REQUEST);
    }



}
