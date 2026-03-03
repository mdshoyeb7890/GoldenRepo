package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.entity.InventoryEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Explicit Spring bean implementation of {@link InventoryMapper}.
 * This avoids relying on MapStruct-generated beans at runtime while
 * keeping the same mapping logic.
 */
@Component
@Primary
public class InventoryMapperSpringAdapter implements InventoryMapper {

    @Override
    public InventoryResponseDto toResponseDto(InventoryEntity inventoryEntity) {
        if (inventoryEntity == null) {
            return null;
        }

        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setId(inventoryEntity.getId());
        dto.setProductId(inventoryEntity.getProductId());
        dto.setProductName(inventoryEntity.getProductName());
        dto.setQuantity(inventoryEntity.getQuantity());
        dto.setReorderLevel(inventoryEntity.getReorderLevel());
        dto.setUnitPrice(inventoryEntity.getUnitPrice());
        dto.setCreatedAt(inventoryEntity.getCreatedAt());
        dto.setUpdatedAt(inventoryEntity.getUpdatedAt());
        return dto;
    }

    @Override
    public InventoryEntity toEntity(InventoryRequestDto inventoryRequestDto) {
        if (inventoryRequestDto == null) {
            return null;
        }

        InventoryEntity entity = new InventoryEntity();
        entity.setProductId(inventoryRequestDto.getProductId());
        entity.setProductName(inventoryRequestDto.getProductName());
        entity.setQuantity(inventoryRequestDto.getQuantity());
        entity.setReorderLevel(inventoryRequestDto.getReorderLevel());
        entity.setUnitPrice(inventoryRequestDto.getUnitPrice());
        return entity;
    }

    @Override
    public void updateEntityFromDto(InventoryRequestDto inventoryRequestDto, InventoryEntity inventoryEntity) {
        if (inventoryRequestDto == null || inventoryEntity == null) {
            return;
        }

        inventoryEntity.setProductId(inventoryRequestDto.getProductId());
        inventoryEntity.setProductName(inventoryRequestDto.getProductName());
        inventoryEntity.setQuantity(inventoryRequestDto.getQuantity());
        inventoryEntity.setReorderLevel(inventoryRequestDto.getReorderLevel());
        inventoryEntity.setUnitPrice(inventoryRequestDto.getUnitPrice());
    }
}

