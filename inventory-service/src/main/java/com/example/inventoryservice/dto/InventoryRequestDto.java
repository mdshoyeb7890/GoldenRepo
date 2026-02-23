package com.example.inventoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InventoryRequestDto {

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive integer")
    private Integer quantity;

    @NotNull(message = "Reorder level is required")
    @Positive(message = "Reorder level must be a positive integer")
    private Integer reorderLevel;

    @NotNull(message = "Unit price is required")
    @Positive(message = "Unit price must be a positive number")
    private Double unitPrice;

}
