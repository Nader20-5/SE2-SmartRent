package com.smartrent.property.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<?> handlePropertyNotFound(PropertyNotFoundException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(UnauthorizedOwnerException.class)
    public ResponseEntity<?> handleUnauthorizedOwner(UnauthorizedOwnerException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(PropertyNotApprovedException.class)
    public ResponseEntity<?> handlePropertyNotApproved(PropertyNotApprovedException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<?> handleInvalidFileType(InvalidFileTypeException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<?> handleFileSizeLimitExceeded(FileSizeLimitExceededException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(DuplicateFavoriteException.class)
    public ResponseEntity<?> handleDuplicateFavorite(DuplicateFavoriteException ex) {
        // TODO: build error response
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        // TODO: build error response
        return null;
    }
}
