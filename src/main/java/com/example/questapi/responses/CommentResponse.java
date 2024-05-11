package com.example.questapi.responses;

import com.example.questapi.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    Long userId;
    String text;

    public CommentResponse(Comment comment) {
        this.userId = comment.getUser().getId();
        this.text = comment.getText();
    }
}
