package com.example.FirstDemo.Service;

import com.example.FirstDemo.entity.UserEntity;

import java.util.List;

public interface UserService {

    public UserEntity create(UserEntity user);
    public UserEntity findByIdUser(Long id);
    public List<UserEntity >findAll1();
    public UserEntity updateUser(Long id, UserEntity user);
    public void deleteUser(Long id);
}
