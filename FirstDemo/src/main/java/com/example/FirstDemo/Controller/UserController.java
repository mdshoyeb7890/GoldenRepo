package com.example.FirstDemo.Controller;

import com.example.FirstDemo.Service.UserService;
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
    public ResponseEntity<UserEntity> create1(@RequestBody UserEntity user) {
        UserEntity createUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Long id) {
        UserEntity get1 = userService.findByIdUser(id);
        return ResponseEntity.ok(get1);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userService.findAll1());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUserDetails(
            @PathVariable Long id,
            @RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("user deleted successfully");
    }
}
