package com.hans.petfinderv1.services;

import com.hans.petfinderv1.Constants;
import com.hans.petfinderv1.exception.NotFoundException;
import com.hans.petfinderv1.model.dto.AuthDto;
import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.User;
import com.hans.petfinderv1.repository.UserRepository;
import com.hans.petfinderv1.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthDto register(UserDto userDto) {
        UserDto newUserDto = Optional.ofNullable(userService.findByEmail(userDto.getEmail()))
                .orElseThrow(() -> new DataIntegrityViolationException(Constants.MAIL_NOT_FOUND.getMessage()));
        userService.create(newUserDto);
        return AuthDto.builder()
                .response(Constants.USER_SUCCESSFULLY_REGISTERED.getMessage())
                .build();
    }

    public AuthDto login(AuthDto authDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(authDto.getEmail())
                .orElseThrow(() -> new NotFoundException(Constants.USER_NOT_FOUND.getMessage())));
        String token = tokenUtil.userToken(user.get());
        return AuthDto.builder()
                .response(token)
                .build();
    }
}
