package com.hans.petfinderv1.controllers;

import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.User;
import com.hans.petfinderv1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK.value()).body(userList);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
       UserDto newUser = userService.create(userDto);
       return ResponseEntity.status(HttpStatus.CREATED.value()).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto userUpdated = userService.update(id,userDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(userUpdated);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String deletedUser = userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(deletedUser);
    }
}
