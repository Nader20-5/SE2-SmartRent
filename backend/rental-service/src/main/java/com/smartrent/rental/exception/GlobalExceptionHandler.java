package com.smartrent.rental.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RentalApplicationNotFoundException.class)
    public ResponseEntity<?> handleNotFound(RentalApplicationNotFoundException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(DuplicateApplicationException.class)
    public ResponseEntity<?> handleDuplicate(DuplicateApplicationException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(InvalidApplicationStatusException.class)
    public ResponseEntity<?> handleInvalidStatus(InvalidApplicationStatusException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(DocumentUploadException.class)
    public ResponseEntity<?> handleDocumentUpload(DocumentUploadException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(UnauthorizedRentalActionException.class)
    public ResponseEntity<?> handleUnauthorized(UnauthorizedRentalActionException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        // TODO: build error response
        return null;
    }
}
