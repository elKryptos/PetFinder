package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.exceptions.ResourceNotFoundException;
import hans.startup.petfinderbackend.models.dtos.UserFormDTO;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.BackendResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<BackendResponse> findUserById (int id){
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new BackendResponse("User with id " + id + " not found"));
        } else {
            User user = userRepository.findById(id).get();
            return ResponseEntity.status(200).body(new BackendResponse("User" + id + "found", user));
        }
    }

}
