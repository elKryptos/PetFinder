package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.UserDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
@CrossOrigin
public class UserController {
    UserService userService;

    @GetMapping("/all")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BackendResponse> findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<BackendResponse> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<BackendResponse> login(@RequestBody UserDto userDto, HttpSession session) {
        return userService.loginUser(userDto, session);
    }

    @PostMapping("/private")
    public ResponseEntity<BackendResponse> privateArea(HttpSession session, @RequestHeader ("Authorization") String auth) {
        return userService.privateArea(session, auth);
    }

    @PostMapping("/logout")
    public ResponseEntity<BackendResponse> logout(HttpSession session) {
        return userService.logout(session);
    }

}
