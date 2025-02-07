package com.hans.petfinderv1.services;

import com.hans.petfinderv1.Constants;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final UserMapper userMapper;

    public List<TokenBlacklist> findByUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        List<TokenBlacklist> tokenBlacklist = tokenBlacklistRepository.findAllByUser(user);
        return tokenBlacklist;
    }

    public void addToBlacklist(String token, LocalDateTime expiration, UserDto userDto) {
        System.out.println("UserId: " + userDto.getUserId() + " added to the blacklist");
        if (userDto == null || userDto.getUserId() == null) {
            throw new IllegalArgumentException(Constants.USER_INFO_MISSING.getMessage());
        }
        User user = userMapper.toEntity(userDto);
        TokenBlacklist tokenBlacklist = new TokenBlacklist(null, token, expiration, user);
        tokenBlacklistRepository.save(tokenBlacklist);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.findByToken(token).isPresent();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanExpiredTokens() {
        try {
            tokenBlacklistRepository.deleteAllByExpirationBefore(LocalDateTime.now());
            System.out.println(Constants.EXPIRED_TOKEN.getMessage());
        } catch (Exception e) {
            System.out.println(Constants.ERROR_REMOVING_EXPIRED_TOKEN.getMessage() + e.getMessage());
        }
    }
}
