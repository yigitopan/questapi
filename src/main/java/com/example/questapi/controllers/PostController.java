package com.example.questapi.controllers;

import com.example.questapi.entities.Post;
import com.example.questapi.requests.PostCreateRequest;
import com.example.questapi.requests.PostUpdateRequest;
import com.example.questapi.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePost(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPost) {
        return postService.createOnePost(newPost);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatedPost) {
        return postService.updateOnePost(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePost(postId);
    }



}
