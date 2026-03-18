package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.exception.InventoryNotFoundException;
import com.example.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class InventoryControllerTest {

    private static final String VALID_REQUEST_JSON =
            "{\"productId\":\"PROD-001\",\"productName\":\"Test Product\",\"quantity\":100,\"reorderLevel\":10,\"unitPrice\":29.99}";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockitoBean
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("POST /api/v1/inventory should create inventory and return 200")
    void createInventory_shouldReturnOk() throws Exception {
        InventoryResponseDto response = new InventoryResponseDto(
                1L,
                "PROD-001",
                "Test Product",
                100,
                10,
                29.99,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(inventoryService.createInventory(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_REQUEST_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productId").value("PROD-001"))
                .andExpect(jsonPath("$.productName").value("Test Product"))
                .andExpect(jsonPath("$.quantity").value(100));
    }

    @Test
    @DisplayName("POST /api/v1/inventory with invalid body should return 400")
    void createInventory_withInvalidBody_shouldReturnBadRequest() throws Exception {
        String invalidJson = "{\"productId\":\"\",\"productName\":\"\",\"quantity\":-1,\"reorderLevel\":-1,\"unitPrice\":-1}";

        mockMvc.perform(post("/api/v1/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /api/v1/inventory should return all inventories and 200")
    void getAllInventories_shouldReturnOk() throws Exception {
        InventoryResponseDto item = new InventoryResponseDto(
                1L,
                "PROD-001",
                "Test Product",
                100,
                10,
                29.99,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(inventoryService.getAllInventories()).thenReturn(List.of(item));

        mockMvc.perform(get("/api/v1/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].productId").value("PROD-001"))
                .andExpect(jsonPath("$[0].productName").value("Test Product"));
    }

    @Test
    @DisplayName("GET /api/v1/inventory should return empty list when no inventories")
    void getAllInventories_whenEmpty_shouldReturnOkWithEmptyList() throws Exception {
        when(inventoryService.getAllInventories()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("GET /api/v1/inventory/{id} should return inventory when found")
    void getInventoryById_shouldReturnOk() throws Exception {
        InventoryResponseDto response = new InventoryResponseDto(
                1L,
                "PROD-001",
                "Test Product",
                100,
                10,
                29.99,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(inventoryService.getInventoryById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productId").value("PROD-001"));
    }

    @Test
    @DisplayName("GET /api/v1/inventory/{id} should return 404 when not found")
    void getInventoryById_whenNotFound_shouldReturn404() throws Exception {
        when(inventoryService.getInventoryById(999L))
                .thenThrow(new InventoryNotFoundException("Inventory not found with ID: 999"));

        mockMvc.perform(get("/api/v1/inventory/999"))
                .andExpect(status().isNotFound());
    }
}
