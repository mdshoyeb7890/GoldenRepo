package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    Logger log = LogManager.getLogger(InventoryController.class);

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponseDto> createInventory(@Valid @RequestBody InventoryRequestDto  inventoryRequestDto) {
        log.info("Received request to create inventory for product: {}", inventoryRequestDto.getProductName());
        InventoryResponseDto createdInventory = inventoryService.createInventory(inventoryRequestDto);
        return ResponseEntity.ok(createdInventory);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDto>> getAllInventories() {
        log.info("Received request to fetch all inventories");
        List<InventoryResponseDto> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> getInventoryById(@PathVariable Long id) {
        log.info("Received request to fetch inventory with ID: {}", id);
        InventoryResponseDto inventoryResponseDto = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventoryResponseDto);
    }
}
