package com.hans.petfinderv1.services;

import com.hans.petfinderv1.Constants;
import com.hans.petfinderv1.exception.DataIntegrityViolationException;
import com.hans.petfinderv1.exception.NotFoundException;
import com.hans.petfinderv1.model.dto.AuthDto;
import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.User;
import com.hans.petfinderv1.repository.TokenBlacklistRepository;
import com.hans.petfinderv1.repository.UserRepository;
import com.hans.petfinderv1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthDto register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DataIntegrityViolationException(Constants.USE_ANOTHER_EMAIL.getMessage());
        }
        userService.create(userDto);
        return AuthDto.builder()
                .response(Constants.USER_SUCCESSFULLY_REGISTERED.getMessage())
                .build();
    }

    public AuthDto login(AuthDto authDto) {
        UserDto userDto = userService.findByEmail(authDto.getEmail());
        if (userDto == null) {
            throw new NotFoundException(Constants.USER_NOT_FOUND.getMessage());
        }
        String token = tokenUtil.userToken(userDto);
        return AuthDto.builder()
                .response(token)
                .build();
    }

    public AuthDto logout(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException(Constants.TOKEN_NOT_VALID.getMessage());
        }
        if (token.startsWith("Bearer")) {
            token = token.substring(7).trim();
        }
        Jws<Claims> claimsJws = tokenUtil.allClaimsJws(token);
        Long userId = claimsJws.getBody().get("UserId", Long.class);
        UserDto userDto = userService.findById(userId);
        if (userDto == null) {
            throw new NotFoundException(Constants.USER_NOT_FOUND.getMessage());
        }
        Date expirationDate = claimsJws.getBody().getExpiration();
        LocalDateTime expirationLocalDate = expirationDate.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        tokenBlacklistService.addToBlacklist(token, expirationLocalDate, userDto);
        return AuthDto.builder()
                .response(Constants.LOGOUT_SUCCESSFULLY.getMessage())
                .build();
    }
}
