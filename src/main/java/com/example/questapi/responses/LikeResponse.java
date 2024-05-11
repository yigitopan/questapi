package com.example.questapi.responses;

import com.example.questapi.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    Long id;
    Long userId;
    Long postId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.postId = like.getPost().getId();
    }
}
