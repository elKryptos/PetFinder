package com.hans.petfinderv1.utils;

import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Getter
public class TokenUtil {

    @Value("${jwt.secret.key}")
    private String privateKey;

    private final UserRepository userRepository;

    public String userToken(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail());
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", userDto.getUserId());
        claims.put("Email", userDto.getEmail());
        claims.put("Firstname", userDto.getFirstname());
        claims.put("Lastname", userDto.getLastname());
        return tokenGenerator(claims);
    }

    public SecretKey decoder(String privateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
            SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
            return secretKey;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String tokenGenerator(Map<String, Object> claims) {
        SecretKey secretKey = decoder(privateKey);
        return Jwts.builder()
                .claims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                .signWith(secretKey)
                .compact();
    }

    public Jws<Claims> allClaimsJws(String token) {
        if(token == null) return null;
        try {
            SecretKey key = decoder(privateKey);
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token);
        } catch(Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Boolean isTokenExpired(String token) {
        if(token.startsWith("Bearer")) token = token.substring(7).trim();
        SecretKey secretKey = decoder(privateKey);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Date expiration = claims.getExpiration();
        // Cambiato il controllo per restituire true se il token è scaduto
        return expiration.before(new Date());
    }
}
