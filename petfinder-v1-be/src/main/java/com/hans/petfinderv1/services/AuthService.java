package com.hans.petfinderv1.services;

import com.hans.petfinderv1.Constants;
import com.hans.petfinderv1.exception.DataIntegrityViolationException;
import com.hans.petfinderv1.exception.NotFoundException;
import com.hans.petfinderv1.model.dto.AuthDto;
import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.User;
import com.hans.petfinderv1.repository.UserRepository;
import com.hans.petfinderv1.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthDto register(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new DataIntegrityViolationException(Constants.USE_ANOTHER_EMAIL.getMessage());
        }
        userService.create(userDto);
        return AuthDto.builder()
                .response(Constants.USER_SUCCESSFULLY_REGISTERED.getMessage())
                .build();
    }

//    public AuthDto login(AuthDto authDto) {
//        Optional<User> user = userRepository.findByEmail(authDto.getEmail());
//        String token = tokenUtil.userToken(user.getEmail(), user.getFirstname());
//        return AuthDto.builder()
//                .response(token)
//                .build();
//    }
}
