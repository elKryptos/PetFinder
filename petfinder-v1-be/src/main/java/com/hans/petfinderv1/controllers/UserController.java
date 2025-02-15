package com.hans.petfinderv1.controllers;

import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
       UserDto newUser = userService.create(userDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> update(@PathVariable Long userId, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.update(userId, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @PostMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId) {
        String deletedUser = userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        UserDto userDto = userService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
