package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.entity.InventoryEntity;
import com.example.inventoryservice.exception.InventoryNotFoundException;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    private InventoryRequestDto requestDto;
    private InventoryEntity entity;
    private InventoryResponseDto responseDto;

    @BeforeEach
    void setUp() {
        requestDto = new InventoryRequestDto(
                "PROD-001",
                "Test Product",
                100,
                10,
                29.99
        );
        entity = new InventoryEntity(
                1L,
                "PROD-001",
                "Test Product",
                100,
                10,
                29.99,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        responseDto = new InventoryResponseDto(
                1L,
                "PROD-001",
                "Test Product",
                100,
                10,
                29.99,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("createInventory should save entity and return response DTO")
    void createInventory_shouldSaveAndReturnResponse() {
        when(inventoryMapper.toEntity(requestDto)).thenReturn(entity);
        when(inventoryRepository.save(any(InventoryEntity.class))).thenReturn(entity);
        when(inventoryMapper.toResponseDto(entity)).thenReturn(responseDto);

        InventoryResponseDto result = inventoryService.createInventory(requestDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getProductId()).isEqualTo("PROD-001");
        assertThat(result.getProductName()).isEqualTo("Test Product");
        assertThat(result.getQuantity()).isEqualTo(100);
        verify(inventoryRepository).save(any(InventoryEntity.class));
        verify(inventoryMapper).toEntity(requestDto);
        verify(inventoryMapper).toResponseDto(entity);
    }

    @Test
    @DisplayName("getInventoryById should return response when entity exists")
    void getInventoryById_shouldReturnResponseWhenExists() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(inventoryMapper.toResponseDto(entity)).thenReturn(responseDto);

        InventoryResponseDto result = inventoryService.getInventoryById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(inventoryRepository).findById(1L);
        verify(inventoryMapper).toResponseDto(entity);
    }

    @Test
    @DisplayName("getInventoryById should throw when entity not found")
    void getInventoryById_shouldThrowWhenNotFound() {
        when(inventoryRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> inventoryService.getInventoryById(999L))
                .isInstanceOf(InventoryNotFoundException.class)
                .hasMessageContaining("Inventory not found with ID: 999");

        verify(inventoryRepository).findById(999L);
        verify(inventoryMapper, never()).toResponseDto(any());
    }

    @Test
    @DisplayName("getAllInventories should return list of response DTOs")
    void getAllInventories_shouldReturnList() {
        List<InventoryEntity> entities = List.of(entity);
        when(inventoryRepository.findAll()).thenReturn(entities);
        when(inventoryMapper.toResponseDto(entity)).thenReturn(responseDto);

        List<InventoryResponseDto> result = inventoryService.getAllInventories();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        verify(inventoryRepository).findAll();
    }

    @Test
    @DisplayName("getAllInventories should return empty list when no inventories")
    void getAllInventories_shouldReturnEmptyList() {
        when(inventoryRepository.findAll()).thenReturn(List.of());

        List<InventoryResponseDto> result = inventoryService.getAllInventories();

        assertThat(result).isEmpty();
        verify(inventoryRepository).findAll();
    }

    @Test
    @DisplayName("updateInventory should update and return response when entity exists")
    void updateInventory_shouldUpdateAndReturnWhenExists() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(inventoryRepository.save(entity)).thenReturn(entity);
        when(inventoryMapper.toResponseDto(entity)).thenReturn(responseDto);

        InventoryResponseDto result = inventoryService.updateInventory(1L, requestDto);

        assertThat(result).isNotNull();
        verify(inventoryMapper).updateEntityFromDto(requestDto, entity);
        verify(inventoryRepository).save(entity);
    }

    @Test
    @DisplayName("updateInventory should throw when entity not found")
    void updateInventory_shouldThrowWhenNotFound() {
        when(inventoryRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> inventoryService.updateInventory(999L, requestDto))
                .isInstanceOf(InventoryNotFoundException.class)
                .hasMessageContaining("Inventory not found with ID: 999");

        verify(inventoryRepository).findById(999L);
        verify(inventoryRepository, never()).save(any());
    }

    @Test
    @DisplayName("deleteInventory should delete when entity exists")
    void deleteInventory_shouldDeleteWhenExists() {
        when(inventoryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(inventoryRepository).deleteById(1L);

        inventoryService.deleteInventory(1L);

        verify(inventoryRepository).existsById(1L);
        verify(inventoryRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deleteInventory should throw when entity not found")
    void deleteInventory_shouldThrowWhenNotFound() {
        when(inventoryRepository.existsById(999L)).thenReturn(false);

        assertThatThrownBy(() -> inventoryService.deleteInventory(999L))
                .isInstanceOf(InventoryNotFoundException.class)
                .hasMessageContaining("Inventory not found with ID: 999");

        verify(inventoryRepository).existsById(999L);
        verify(inventoryRepository, never()).deleteById(any());
    }
}
