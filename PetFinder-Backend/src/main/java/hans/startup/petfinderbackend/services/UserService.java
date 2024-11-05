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
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public Response<UserDto> findUserById (int id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found"));
        return new Response<>("User found", userMapper.toDto(user));

    }

    public Response<UserDto> createUser(UserDto userDto) {
        User user = new User();
        return null;
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
        String email = claims.getPayload().get("email", String.class);
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
