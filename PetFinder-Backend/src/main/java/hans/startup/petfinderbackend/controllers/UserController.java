package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.UserDto;
import hans.startup.petfinderbackend.models.mappers.UserMapper;
import hans.startup.petfinderbackend.responses.Response;
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
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<UserDto>> createUser(@RequestBody UserDto userDto) {
        Response<UserDto> response = userService.createUser(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody UserDto userDto, HttpSession session) {
        Response<String> response = userService.loginUser(userDto, session);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/private")
    public ResponseEntity<Response<String>> privateArea(HttpSession session, @RequestHeader ("Authorization") String auth) {
        Response<String> response = userService.privateArea(session, auth);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logout(HttpSession session) {
        Response<String> response = userService.logout(session);
        return ResponseEntity.ok(response);
    }

}
