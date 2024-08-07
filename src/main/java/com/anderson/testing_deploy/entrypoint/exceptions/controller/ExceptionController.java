package com.anderson.testing_deploy.entrypoint.exceptions.controller;

import com.anderson.testing_deploy.core.exceptions.DataConflictException;
import com.anderson.testing_deploy.core.exceptions.NotFoundException;
import com.anderson.testing_deploy.core.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static com.anderson.testing_deploy.core.exceptions.constants.ExceptionConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<StandardException> dataConflict(DataConflictException e, HttpServletRequest request) {
        var exception = new StandardException(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException e, HttpServletRequest request) {
        var exception = new StandardException(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardException> generic(Exception e, HttpServletRequest request) {
        var exception = new StandardException(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

}
