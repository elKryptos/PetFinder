package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.services.UserService;
import lombok.AllArgsConstructor;
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
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

}
