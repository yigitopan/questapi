package com.example.questapi.repos;

import com.example.questapi.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByToken(String token);

    void deleteByToken(String token);

    RefreshToken findByUserId(Long userId);
}
