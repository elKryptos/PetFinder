package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
