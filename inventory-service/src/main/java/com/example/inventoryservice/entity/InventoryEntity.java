package com.example.inventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String productId;

    @NotNull
    @Column(nullable = false)
    private Integer productName;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer reorderLevel;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(name= "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name= "updated_at")
    private LocalDateTime updatedAt;
}
