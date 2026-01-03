package com.example.FirstDemo.Controller;

import com.example.FirstDemo.Service.UserService;
import com.example.FirstDemo.dto.UserRequestDto;
import com.example.FirstDemo.dto.UserResponseDto;
import com.example.FirstDemo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.awt.image.LookupOp;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create1(@RequestBody UserRequestDto user) {
        UserResponseDto createUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto get1 = userService.findByIdUser(id);
        return ResponseEntity.ok(get1);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.findAll1());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUserDetails(
            @PathVariable Long id,
            @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("user deleted successfully");
    }
}
