package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.dtos.UserFormDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
public class UserController {
    UserService userService;

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BackendResponse> findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<BackendResponse> createUser(@RequestBody UserFormDto userFormDto) {
        return userService.createUser(userFormDto);
    }

}
