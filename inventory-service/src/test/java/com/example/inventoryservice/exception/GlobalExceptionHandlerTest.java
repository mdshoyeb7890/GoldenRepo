package com.example.inventoryservice.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("handleInventoryNotFoundException returns 404 and NOT_FOUND")
    void handleInventoryNotFoundException_returns404() {
        InventoryNotFoundException ex = new InventoryNotFoundException("Inventory not found with ID: 1");

        ResponseEntity<ErrorResponse> response = handler.handleInventoryNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrorCode()).isEqualTo("NOT_FOUND");
        assertThat(response.getBody().getMessage()).isEqualTo("Inventory not found with ID: 1");
        assertThat(response.getBody().getStatus()).isEqualTo(404);
    }

    @Test
    @DisplayName("handleInvalidInventoryDataException returns 400 and INVALID_DATA")
    void handleInvalidInventoryDataException_returns400() {
        InvalidInventoryDataException ex = new InvalidInventoryDataException("Invalid quantity");

        ResponseEntity<ErrorResponse> response = handler.handleInvalidInventoryDataException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrorCode()).isEqualTo("INVALID_DATA");
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid quantity");
        assertThat(response.getBody().getStatus()).isEqualTo(400);
    }

    @Test
    @DisplayName("handleInventoryAlreadyExistException returns 409 and ALREADY_EXISTS")
    void handleInventoryAlreadyExistException_returns409() {
        InventoryAlreadyExistException ex = new InventoryAlreadyExistException("Product already in inventory");

        ResponseEntity<ErrorResponse> response = handler.handleInventoryAlreadyExistException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrorCode()).isEqualTo("ALREADY_EXISTS");
        assertThat(response.getBody().getMessage()).isEqualTo("Product already in inventory");
        assertThat(response.getBody().getStatus()).isEqualTo(409);
    }

    @Test
    @DisplayName("handleGenericException returns 500 and INTERNAL_SERVER_ERROR")
    void handleGenericException_returns500() {
        Exception ex = new RuntimeException("Unexpected failure");

        ResponseEntity<ErrorResponse> response = handler.handleGenericException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrorCode()).isEqualTo("INTERNAL_SERVER_ERROR");
        assertThat(response.getBody().getMessage()).isEqualTo("An unexpected error occurred. Please try again later.");
        assertThat(response.getBody().getStatus()).isEqualTo(500);
    }
}
