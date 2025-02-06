package com.hans.petfinderv1.repository;

import com.hans.petfinderv1.model.entity.TokenBlacklist;
import com.hans.petfinderv1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {

    Optional<TokenBlacklist> findByToken(String token);
    List<TokenBlacklist> findAllByUser(User user);
    void deleteAllByExpirationBefore(LocalDateTime now);
}
