package com.example.FirstDemo.Service;

import com.example.FirstDemo.Repository.UserRepo;
import com.example.FirstDemo.dto.UserRequestDto;
import com.example.FirstDemo.dto.UserResponseDto;
import com.example.FirstDemo.entity.UserEntity;
import com.example.FirstDemo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;


    @Override
    public UserResponseDto create(UserRequestDto user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved =  userRepo.save(entity);
        return UserMapper.toDto(saved);
    }

    @Override
    public UserResponseDto findByIdUser(Long id) {
        UserEntity user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Id does not exist"));
        return  UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> findAll1() {

        return userRepo.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto user) {
        UserEntity existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Id does not exist"));

        existingUser.setName(user.getName());
        existingUser.setCity(user.getCity());

        UserEntity entity = userRepo.save(existingUser);
        return  UserMapper.toDto(entity);
    }

    

    @Override
    public void deleteUser(Long id) {

        if(!userRepo.existsById(id)) {
            throw new RuntimeException("Id does not exist");
        }
        userRepo.deleteById(id);
    }
}
