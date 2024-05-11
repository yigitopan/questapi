package com.example.questapi.services;

import com.example.questapi.entities.Post;
import com.example.questapi.entities.User;
import com.example.questapi.repos.LikeRepository;
import com.example.questapi.requests.LikeRequest;
import com.example.questapi.responses.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.example.questapi.entities.Like;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {
    @Autowired
    @Lazy
    PostService postService;
    LikeRepository likeRepository;
    UserService userService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Like createLike(LikeRequest likeRequest) {
        Like like = new Like();
        User user = userService.getOneUser(likeRequest.getUserId());
        Post post = postService.getOnePost(likeRequest.getPostId());

        if (user == null || post == null) {
            return null;
        }
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
        return like;
    }

    public List<LikeResponse> getLikesOfPost(Long postId) {
        List<Like> Likes = likeRepository.findByPostId(postId);
        return Likes.stream().map(LikeResponse::new).collect(Collectors.toList());
    }
}
