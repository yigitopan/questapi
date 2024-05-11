package com.example.questapi.controllers;


import com.example.questapi.entities.Comment;
import com.example.questapi.requests.CommentCreateRequest;
import com.example.questapi.requests.CommentUpdateRequest;
import com.example.questapi.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return commentService.getAllComments(postId, userId);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@RequestParam Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updatedComment) {
        return commentService.updateComment(commentId, updatedComment);
    }

    @DeleteMapping
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
    }


}
