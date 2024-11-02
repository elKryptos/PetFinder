package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.UserDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.models.mappers.UserMapper;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.Response;
import hans.startup.petfinderbackend.responses.UserResponse;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;

    public Response<List<UserDto>> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = userMapper.toDtoList(users);
        return new Response<>("Users found", userDtos);
    }

    public ResponseEntity<UserResponse> findUserById (int id){
        if (!userRepository.existsById(id)) {
            return ResponseEntity
                    .status(404)
                    .body(new UserResponse("User with id " + id + " not found"));
        } else {
            User user = userRepository.findById(id).get();
            return ResponseEntity
                    .status(200)
                    .body(new UserResponse("User " + id + " found", user));
        }
    }

    public ResponseEntity<UserResponse> createUser(UserDto userDto) {
        User user = new User();
        if (userDto.getFirstname() == null || userDto.getFirstname().isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body(new UserResponse("Firstname cannot be empty"));
        } else {
            user.setFirstname(userDto.getFirstname());
        }
        if (userDto.getLastname() == null || userDto.getLastname().isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body(new UserResponse("Lastname cannot be empty"));
        } else {
            user.setLastname(userDto.getLastname());
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body(new UserResponse("Empty or Invalid email address"));
        } else if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .status(400)
                    .body(new UserResponse("Email address already in use"));
        } else {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body(new UserResponse("Password cannot be empty"));
        } else {
            try {
                user.setPassword(encoder.encode(userDto.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity
                        .status(400)
                        .body(new UserResponse("Invalid password | Password cannot be empty | Password not hashed"));
            }
        }
        user.setRegistrationDate(LocalDateTime.now());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(new UserResponse("Error creating user"));
        }
        return ResponseEntity
                .status(201)
                .body(new UserResponse("User created", user));
    }

    public ResponseEntity<UserResponse> loginUser(UserDto userDto, HttpSession session) {
        if (userDto == null || userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            return ResponseEntity
                    .status(401)
                    .body(new UserResponse("Email address/password are missing or invalid"));
        }

        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null){
            return ResponseEntity
                    .status(401)
                    .body(new UserResponse("Email address not found"));
        }
        if (!encoder.matches(userDto.getPassword(), user.getPassword())){
            return ResponseEntity
                    .status(401)
                    .body(new UserResponse("Invalid password"));
        }
        String userToken = JwtToken.tokenGenerator(user.getFirstname(), user.getLastname(), user.getEmail());
        session.setAttribute("userToken", userToken);
        session.setAttribute("email", user.getEmail());
        return ResponseEntity
                .status(200)
                .body(new UserResponse(userToken, user.getEmail()));
    }

    public ResponseEntity<UserResponse> privateArea(HttpSession session, String auth) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        System.out.println(claims);
        if (claims == null) {
            return ResponseEntity
                    .status(401)
                    .body(new UserResponse("Invalid token"));
        }
        String email = claims.getBody().get("email", String.class);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new UserResponse("Email address not found"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UserResponse("Logged in private area", claims.getBody()));
    }

    public static Set<String> revokedTokens = new HashSet<>();

    public ResponseEntity<UserResponse> logout (HttpSession session) {
        String token = (String) session.getAttribute("userToken");
        if (token != null) {
            revokedTokens.add(token);
        }
        session.removeAttribute("userToken");
        session.removeAttribute("email");
        return ResponseEntity
                .status(200)
                .body(new UserResponse("Logged out"));
    }
}
