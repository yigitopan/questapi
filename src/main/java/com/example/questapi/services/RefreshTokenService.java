package com.example.questapi.services;

import com.example.questapi.entities.RefreshToken;
import com.example.questapi.entities.User;
import com.example.questapi.repos.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public boolean isRefreshExpired (RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }

    public String createRefreshToken (User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Date.from(Instant.now().plusSeconds(864000))); // 10 days, best practice is to store this in application.properties
        return refreshTokenRepository.save(refreshToken).getToken();
    }

    public RefreshToken getByUserId(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }
}
