package com.hans.petfinderv1.services;

import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.TokenBlacklist;
import com.hans.petfinderv1.model.entity.User;
import com.hans.petfinderv1.model.mapper.UserMapper;
import com.hans.petfinderv1.repository.TokenBlacklistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final UserMapper userMapper;

    public void addToBlacklist(String token, LocalDateTime expiration, UserDto userDto) {
        System.out.println("UserId: " + userDto.getUserId() + " added to the blacklist");
        if (userDto == null || userDto.getUserId() == null) {
            throw new IllegalArgumentException("User information is incomplete.");
        }
        User user = userMapper.toEntity(userDto);
        TokenBlacklist tokenBlacklist = new TokenBlacklist(null, token, expiration, user);
        tokenBlacklistRepository.save(tokenBlacklist);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.findByToken(token).isPresent();
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void cleanExpiredTokens() {
        try {
            tokenBlacklistRepository.deleteAllByExpirationBefore(LocalDateTime.now());
            System.out.println("Expired tokens removed");
        } catch (Exception e) {
            System.out.println("Error removing expired tokens" + e.getMessage());
        }
    }
}
