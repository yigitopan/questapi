package com.example.questapi.services;

import com.example.questapi.entities.Post;
import com.example.questapi.entities.User;
import com.example.questapi.repos.PostRepository;
import com.example.questapi.requests.PostCreateRequest;
import com.example.questapi.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    PostRepository postRepository;
    UserService userService;
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        } else {
            return postRepository.findAll();
        }
    }

    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPost) {
        User user = userService.getOneUser(newPost.getUserId());
        if (user == null) {
            return null;
        }

        Post toSave = new Post();
        toSave.setId(newPost.getId());
        toSave.setTitle(newPost.getTitle());
        toSave.setText(newPost.getText());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest newPost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            return null;
        }
        Post toUpdate = post.get();

        toUpdate.setTitle(newPost.getTitle());
        toUpdate.setText(newPost.getText());
        return postRepository.save(toUpdate);
    }
}
