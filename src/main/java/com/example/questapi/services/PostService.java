package com.example.questapi.services;

import com.example.questapi.entities.Comment;
import com.example.questapi.entities.Like;
import com.example.questapi.entities.Post;
import com.example.questapi.entities.User;
import com.example.questapi.repos.PostRepository;
import com.example.questapi.requests.PostCreateRequest;
import com.example.questapi.requests.PostUpdateRequest;
import com.example.questapi.responses.CommentResponse;
import com.example.questapi.responses.LikeResponse;
import com.example.questapi.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    @Lazy
    LikeService likeService;

    @Autowired
    @Lazy
    CommentService commentService;

    PostRepository postRepository;
    UserService userService;
    public PostService(
            PostRepository postRepository,
            UserService userService
    ) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> posts;
        if (userId.isPresent()) {
            posts = postRepository.findByUserId(userId.get());
        } else {
            posts = postRepository.findAll();
        }
        return posts.stream().map(post -> {
            List<LikeResponse> LikeList = likeService.getLikesOfPost(post.getId());
            List<Comment> Comments = commentService.getAllComments(Optional.of(post.getId()), Optional.empty());
            List<CommentResponse> CommentList = Comments.stream().map(CommentResponse::new).collect(Collectors.toList());
            return new PostResponse(post, LikeList, CommentList);
        }).collect(Collectors.toList());
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
