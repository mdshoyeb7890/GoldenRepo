package com.example.FirstDemo.mapper;

import com.example.FirstDemo.dto.UserRequestDto;
import com.example.FirstDemo.dto.UserResponseDto;
import com.example.FirstDemo.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(UserRequestDto dto) {
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setCity(dto.getCity());

        return user;
    }

    public static UserResponseDto toDto(UserEntity entity) {

        UserResponseDto dto = new UserResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setCity(entity.getCity());

        return dto;
    }
}
