package com.example.questapi.requests;


import lombok.Data;

@Data
public class UserLoginRequest {
    String username;
    String password;
}
