package com.example.questapi.responses;

import lombok.Data;

@Data
public class AuthResponse {
    Long userId;
    String refreshToken;
    String accessToken;
    String message;

    public AuthResponse(Long userId, String refreshToken, String accessToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public AuthResponse() {
    }
}
