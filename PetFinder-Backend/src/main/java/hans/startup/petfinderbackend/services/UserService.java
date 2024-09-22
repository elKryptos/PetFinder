package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.UserFormDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.BackendResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ResponseEntity<BackendResponse> createUser(UserFormDto userFormDto) {
        User user = new User();
        if(userFormDto.getFirstname() == null || userFormDto.getFirstname().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Firstname cannot be empty"));
        } else {
            user.setFirstname(userFormDto.getFirstname());
        }
        if(userFormDto.getLastname() == null || userFormDto.getLastname().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Lastname cannot be empty"));
        } else {
            user.setLastname(userFormDto.getLastname());
        }
        if(userFormDto.getEmail() == null || userFormDto.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Empty or Invalid email address"));
        } else if (userRepository.existsByEmail(userFormDto.getEmail())) {
            return ResponseEntity.status(400).body(new BackendResponse("Email address already in use"));
        } else {
            user.setEmail(userFormDto.getEmail());
        }
        if(userFormDto.getPassword() != null) {
            user.setPassword(userFormDto.getPassword());
        } else {
            return ResponseEntity.status(400).body(new BackendResponse("Empty or Invalid password"));
        }
        user.setRegistrationDate(LocalDateTime.now());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new BackendResponse("Error creating user"));
        }
        return ResponseEntity.status(201).body(new BackendResponse("User created", user));
    }

}
