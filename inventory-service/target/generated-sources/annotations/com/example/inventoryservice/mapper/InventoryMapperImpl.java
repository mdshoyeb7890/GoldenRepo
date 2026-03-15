package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.InventoryRequestDto;
import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.entity.InventoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-15T11:40:41+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public InventoryResponseDto toResponseDto(InventoryEntity inventoryEntity) {
        if ( inventoryEntity == null ) {
            return null;
        }

        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto();

        inventoryResponseDto.setId( inventoryEntity.getId() );
        inventoryResponseDto.setProductId( inventoryEntity.getProductId() );
        inventoryResponseDto.setProductName( inventoryEntity.getProductName() );
        inventoryResponseDto.setQuantity( inventoryEntity.getQuantity() );
        inventoryResponseDto.setReorderLevel( inventoryEntity.getReorderLevel() );
        inventoryResponseDto.setUnitPrice( inventoryEntity.getUnitPrice() );
        inventoryResponseDto.setCreatedAt( inventoryEntity.getCreatedAt() );
        inventoryResponseDto.setUpdatedAt( inventoryEntity.getUpdatedAt() );

        return inventoryResponseDto;
    }

    @Override
    public InventoryEntity toEntity(InventoryRequestDto inventoryRequestDto) {
        if ( inventoryRequestDto == null ) {
            return null;
        }

        InventoryEntity inventoryEntity = new InventoryEntity();

        inventoryEntity.setProductId( inventoryRequestDto.getProductId() );
        inventoryEntity.setProductName( inventoryRequestDto.getProductName() );
        inventoryEntity.setQuantity( inventoryRequestDto.getQuantity() );
        inventoryEntity.setReorderLevel( inventoryRequestDto.getReorderLevel() );
        inventoryEntity.setUnitPrice( inventoryRequestDto.getUnitPrice() );

        return inventoryEntity;
    }

    @Override
    public void updateEntityFromDto(InventoryRequestDto inventoryRequestDto, InventoryEntity inventoryEntity) {
        if ( inventoryRequestDto == null ) {
            return;
        }

        inventoryEntity.setProductId( inventoryRequestDto.getProductId() );
        inventoryEntity.setProductName( inventoryRequestDto.getProductName() );
        inventoryEntity.setQuantity( inventoryRequestDto.getQuantity() );
        inventoryEntity.setReorderLevel( inventoryRequestDto.getReorderLevel() );
        inventoryEntity.setUnitPrice( inventoryRequestDto.getUnitPrice() );
    }
}
