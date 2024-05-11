package com.example.questapi.requests;

import lombok.Data;

@Data
public class LikeRequest {
    Long postId;
    Long userId;
}
