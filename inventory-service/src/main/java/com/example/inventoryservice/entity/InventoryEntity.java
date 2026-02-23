package com.example.inventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    private String productName;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer reorderLevel;

    @Column(nullable = false)
    private Double unitPrice;

    @CreatedDate
    @Column(name= "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name= "updated_at")
    private LocalDateTime updatedAt;
}
