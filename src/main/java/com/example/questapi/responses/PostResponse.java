package com.example.questapi.responses;

import com.example.questapi.entities.Comment;
import com.example.questapi.entities.Like;
import lombok.Data;
import com.example.questapi.entities.Post;

import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String username;
    String title;
    String text;
    List<LikeResponse> postLikes;
    List<CommentResponse> postComments;


    public PostResponse(Post post, List<LikeResponse> postLikes, List<CommentResponse> postComments) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.text = post.getText();
        this.postLikes = postLikes;
        this.postComments = postComments;
    }
}
