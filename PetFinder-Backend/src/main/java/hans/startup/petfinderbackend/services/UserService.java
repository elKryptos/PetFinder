package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.UserFormDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.BackendResponse;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder encoder;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<BackendResponse> findUserById (int id){
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new BackendResponse("User with id " + id + " not found"));
        } else {
            User user = userRepository.findById(id).get();
            return ResponseEntity.status(200).body(new BackendResponse("User " + id + " found", user));
        }
    }

    public ResponseEntity<BackendResponse> createUser(UserFormDto userFormDto) {
        User user = new User();
        if (userFormDto.getFirstname() == null || userFormDto.getFirstname().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Firstname cannot be empty"));
        } else {
            user.setFirstname(userFormDto.getFirstname());
        }
        if (userFormDto.getLastname() == null || userFormDto.getLastname().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Lastname cannot be empty"));
        } else {
            user.setLastname(userFormDto.getLastname());
        }
        if (userFormDto.getEmail() == null || userFormDto.getEmail().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Empty or Invalid email address"));
        } else if (userRepository.existsByEmail(userFormDto.getEmail())) {
            return ResponseEntity.status(400).body(new BackendResponse("Email address already in use"));
        } else {
            user.setEmail(userFormDto.getEmail());
        }
        if (userFormDto.getPassword() == null || userFormDto.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body(new BackendResponse("Password cannot be empty"));
        } else {
            try {
                user.setPassword(encoder.encode(userFormDto.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(400).body(new BackendResponse("Invalid password | Password cannot be empty | Password not hashed"));
            }
        }
        user.setRegistrationDate(LocalDateTime.now());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new BackendResponse("Error creating user"));
        }return ResponseEntity.status(201).body(new BackendResponse("User created", user));
    }

    public ResponseEntity<BackendResponse> loginUser(UserFormDto userFormDto, HttpSession session) {
        User user = userRepository.findByEmail(userFormDto.getEmail());
        if ( user == null || userFormDto.getEmail().isEmpty()){
            return ResponseEntity.status(401).body(new BackendResponse("Email address not found"));
        }
        if (!encoder.matches(userFormDto.getPassword(), user.getPassword())){
            return ResponseEntity.status(401).body(new BackendResponse("Invalid password"));
        }
        String userToken = JwtToken.tokenGenerator(user.getFirstname(), user.getLastname(), user.getEmail());
        session.setAttribute("userToken", userToken);
        session.setAttribute("email", user.getEmail());
        return ResponseEntity.status(200).body(new BackendResponse(userToken, user.getEmail()));
    }

    public ResponseEntity<BackendResponse> privateArea(HttpSession session, String auth) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        System.out.println(claims);
        if (claims == null) {
            return ResponseEntity.status(401).body(new BackendResponse("Invalid token"));
        }
        String email = claims.getBody().get("email", String.class);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(401).body(new BackendResponse("Email address not found"));
        }
        return ResponseEntity.status(200).body(new BackendResponse("Logged in private area", claims.getBody()));
    }

    public static Set<String> revokedTokens = new HashSet<>();
    public ResponseEntity<BackendResponse> logout(HttpSession session) {
        String token = (String) session.getAttribute("userToken");
        if (token != null) {
            revokedTokens.add(token);
        }
        session.removeAttribute("userToken");
        session.removeAttribute("email");
        return ResponseEntity.status(200).body(new BackendResponse("Logged out"));
    }
}
