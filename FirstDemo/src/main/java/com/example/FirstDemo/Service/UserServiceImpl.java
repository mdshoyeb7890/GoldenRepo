package com.example.FirstDemo.Service;

import com.example.FirstDemo.Repository.UserRepo;
import com.example.FirstDemo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;


    @Override
    public UserEntity create(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public UserEntity findByIdUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Id does not exist"));
    }

    @Override
    public List<UserEntity> findAll1() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity user) {
        UserEntity existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Id does not exist"));

        existingUser.setName(user.getName());
        existingUser.setCity(user.getCity());

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepo.existsById(id)) {
            throw new RuntimeException("Id does not exist");
        }
        userRepo.deleteById(id);
    }
}
