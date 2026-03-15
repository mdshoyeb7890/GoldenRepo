package com.example.inventoryservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(BindException ex) {

        String message = "Validation failed";

        if (ex.getBindingResult() != null) {
            message = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(err -> {
                        String defaultMessage = err.getDefaultMessage();
                        if (defaultMessage == null) {
                            defaultMessage = "invalid";
                        }
                        return err.getField() + ": " + defaultMessage;
                    })
                    .collect(Collectors.joining("; "));
        }

        if (message.isEmpty()) {
            message = "Validation failed";
        }

        log.error("Validation failed: {}", message);

        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_FAILED",
                message,
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

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
