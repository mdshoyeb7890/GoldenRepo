package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;

import java.util.List;

public interface InventoryService {

    InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto);
    InventoryResponseDto getInventoryById(Long id);
    List<InventoryResponseDto> getAllInventories();
    InventoryResponseDto updateInventory(Long id, InventoryRequestDto inventoryRequestDto);
    void deleteInventory(Long id);

}
