package com.example.questapi.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
    String text;
    String title;
    Long userId;
}
