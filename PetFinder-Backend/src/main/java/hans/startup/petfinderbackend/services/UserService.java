package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }
}
