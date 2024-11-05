package hans.startup.petfinderbackend.services;

import hans.startup.petfinderbackend.models.dtos.UserDto;
import hans.startup.petfinderbackend.models.entities.User;
import hans.startup.petfinderbackend.models.mappers.UserMapper;
import hans.startup.petfinderbackend.repositories.UserRepository;
import hans.startup.petfinderbackend.responses.Response;
import hans.startup.petfinderbackend.utils.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        User user = userMapper.toEntity(userDto);
        user.setRegistrationDate(LocalDateTime.now());
        user.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return new Response<>("User created", userMapper.toDto(user));
    }

    public Response<String> loginUser(UserDto userDto, HttpSession session) {
        if (userDto.getEmail().isEmpty()) {
            throw new RuntimeException("Email non fornita");
        }
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null || userDto.getEmail().isEmpty()){
            throw new RuntimeException("Email or User not trovato");// creare UserNotFoundException
        }
        if (!encoder.matches(userDto.getPassword(), user.getPassword())){
            throw new RuntimeException("Password incorreto"); // creare InvalidPasswordException
        }
        String userToken = JwtToken.tokenGenerator(user.getFirstname(), user.getLastname(), user.getEmail());
        session.setAttribute("userToken", userToken);
        session.setAttribute("email", user.getEmail());
        return new Response<>("Loggato correttamente", userToken);
    }

    public Response<String> privateArea(HttpSession session, String auth) {
        String token = auth.substring(7);
        Jws<Claims> claims = JwtToken.verifyToken(token);
        System.out.println(claims);
        if (claims == null) {
            throw new RuntimeException("Token not valid");
        }
        String email = claims.getPayload().get("email", String.class);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Mail not found");
        }
        return new Response<>("Logged in private area", email);
    }

    public static Set<String> revokedTokens = new HashSet<>();

    public Response<String> logout (HttpSession session) {
        String token = (String) session.getAttribute("userToken");
        if (token != null) {
            revokedTokens.add(token);
        }
        session.removeAttribute("userToken");
        session.removeAttribute("email");
        return new Response<>("Logged out");
    }
}
