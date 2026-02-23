package com.example.inventoryservice.exception;

public class InventoryAlreadyExistException extends RuntimeException {

    public InventoryAlreadyExistException(String message) {
        super(message);
    }

    public InventoryAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
