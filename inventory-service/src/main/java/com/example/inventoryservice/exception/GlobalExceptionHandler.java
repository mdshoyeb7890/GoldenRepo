package com.example.inventoryservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInventoryNotFoundException(InventoryNotFoundException ex) {
        log.error("Inventory not found: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(InvalidInventoryDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInventoryDataException(InvalidInventoryDataException ex) {
        log.error("Invalid inventory data: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("INVALID_DATA", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(InventoryAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleInventoryAlreadyExistException(InventoryAlreadyExistException ex) {
        log.error("Inventory already exists: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("ALREADY_EXISTS", ex.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(409).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(500).body(errorResponse);
    }
}
