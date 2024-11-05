package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.UserDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.models.mappers.UserMapper;
import hans.startup.petfinderbackend.responses.Response;
import hans.startup.petfinderbackend.responses.UserResponse;
import hans.startup.petfinderbackend.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/all")
    public ResponseEntity<Response<List<UserDto>>> allUsers() {
        Response<List<UserDto>> response = userService.allUsers();
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UserDto>> findUserById(@PathVariable int id) {
        Response<UserDto> response = userService.findUserById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserDto userDto, HttpSession session) {
        return userService.loginUser(userDto, session);
    }

    @PostMapping("/private")
    public ResponseEntity<UserResponse> privateArea(HttpSession session, @RequestHeader ("Authorization") String auth) {
        return userService.privateArea(session, auth);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserResponse> logout(HttpSession session) {
        return userService.logout(session);
    }

}
