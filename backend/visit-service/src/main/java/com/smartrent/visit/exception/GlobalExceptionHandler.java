package com.smartrent.visit.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VisitNotFoundException.class)
    public ResponseEntity<?> handleVisitNotFound(VisitNotFoundException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(VisitConflictException.class)
    public ResponseEntity<?> handleVisitConflict(VisitConflictException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(InvalidVisitStatusTransitionException.class)
    public ResponseEntity<?> handleInvalidTransition(InvalidVisitStatusTransitionException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(UnauthorizedVisitActionException.class)
    public ResponseEntity<?> handleUnauthorizedAction(UnauthorizedVisitActionException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        // TODO: build error response
        return null;
    }
}
