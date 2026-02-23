package com.example.inventoryservice.service;


import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.entity.InventoryEntity;
import com.example.inventoryservice.exception.InventoryNotFoundException;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    @Override
    public InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto) {
        log.info("Creating inventory for product: {}", inventoryRequestDto.getProductName());
        InventoryEntity entity = inventoryMapper.toEntity(inventoryRequestDto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        InventoryEntity savedEntity = inventoryRepository.save(entity);
        log.info("Inventory created with ID: {}", savedEntity.getId());

        return inventoryMapper.toResponseDto(savedEntity);
    }

    @Override
    public InventoryResponseDto getInventoryById(Long id) {
        log.info("Fetching inventory with ID: {}", id);
        InventoryEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with ID: " + id));
//        InventoryEntity savedEntity = inventoryRepository.save(entity);
        log.info("Inventory fetched with ID: {}", entity.getId());
        return inventoryMapper.toResponseDto(entity);
    }

    @Override
    public List<InventoryResponseDto> getAllInventories() {
       log.info("Fetching all inventories");
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponseDto)
                .toList();
    }

    @Override
    public InventoryResponseDto updateInventory(Long id, InventoryRequestDto inventoryRequestDto) {
      log.info("Updating inventory with ID: {}", id);
        InventoryEntity existingEntity = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with ID: " + id));
        inventoryMapper.updateEntityFromDto(inventoryRequestDto, existingEntity);
        InventoryEntity updatedEntity = inventoryRepository.save(existingEntity);
        log.info("Inventory updated with ID: {}", updatedEntity.getId());
        return inventoryMapper.toResponseDto(updatedEntity);
    }

    @Override
    public void deleteInventory(Long id) {
        log.info("Deleting inventory with ID: {}", id);
        if (!inventoryRepository.existsById(id)) {
            throw new InventoryNotFoundException("Inventory not found with ID: " + id);
        }
        inventoryRepository.deleteById(id);
        log.info("Inventory deleted with ID: {}", id);

    }
}
