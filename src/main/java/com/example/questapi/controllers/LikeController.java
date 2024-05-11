package com.example.questapi.controllers;


import com.example.questapi.entities.Like;
import com.example.questapi.requests.LikeRequest;
import com.example.questapi.responses.LikeResponse;
import com.example.questapi.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public Like createLike(@RequestBody LikeRequest likeRequest) {
        return likeService.createLike(likeRequest);
    }

    @GetMapping("/{postId}")
    public List<LikeResponse> getLikesOfPost(@PathVariable Long postId) {
        return likeService.getLikesOfPost(postId);
    }
}
