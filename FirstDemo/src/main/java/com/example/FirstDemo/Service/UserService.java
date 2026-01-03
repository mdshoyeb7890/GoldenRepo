package com.example.FirstDemo.Service;

import com.example.FirstDemo.dto.UserRequestDto;
import com.example.FirstDemo.dto.UserResponseDto;
import com.example.FirstDemo.entity.UserEntity;

import java.util.List;

public interface UserService {

    public UserResponseDto create(UserRequestDto user);
    public UserResponseDto findByIdUser(Long id);
    public List<UserResponseDto >findAll1();
    public UserResponseDto updateUser(Long id, UserRequestDto user);
    public void deleteUser(Long id);
}
