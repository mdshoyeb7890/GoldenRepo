package com.example.inventoryservice.exception;

public class InvalidInventoryDataException extends RuntimeException {

    public InvalidInventoryDataException(String message) {
        super(message);
    }

    public InvalidInventoryDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
