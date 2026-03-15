package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.entity.InventoryEntity;

public interface InventoryMapper {

    InventoryResponseDto toResponseDto(InventoryEntity inventoryEntity);

    InventoryEntity toEntity(InventoryRequestDto inventoryRequestDto);

    void updateEntityFromDto(InventoryRequestDto inventoryRequestDto, InventoryEntity inventoryEntity);
}
