package com.example.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {

    private Long id;
    private String productId;
    private String productName;
    private Integer quantity;
    private Integer reorderLevel;
    private Double unitPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
