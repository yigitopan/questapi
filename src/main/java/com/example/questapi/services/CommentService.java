package com.example.questapi.services;

import com.example.questapi.entities.Comment;
import com.example.questapi.entities.Post;
import com.example.questapi.entities.User;
import com.example.questapi.repos.CommentRepository;
import com.example.questapi.requests.CommentCreateRequest;
import com.example.questapi.requests.CommentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    @Lazy
    CommentRepository commentRepository;
    UserService userService;
    PostService postService;

    public CommentService(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if(postId.isPresent() && userId.isPresent()) {
            return commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
        }
        else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest comment) {
       User user = userService.getOneUser(comment.getUserId());
       Post post = postService.getOnePost(comment.getPostId());

       if(user == null || post == null) {
           return null;
       }

       Comment newComment = new Comment();
       newComment.setId(comment.getId());
       newComment.setPost(post);
       newComment.setUser(user);
       newComment.setText(comment.getText());

       return commentRepository.save(newComment);
    }

    public Comment updateComment(Long commentId, CommentUpdateRequest updatedComment) {
       Optional<Comment> commentOptional = commentRepository.findById(commentId);

       if(commentOptional.isPresent()) {
           Comment comment = commentOptional.get();
           comment.setText(updatedComment.getText());
           return commentRepository.save(comment);
       } else {
           return null;
       }

    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
