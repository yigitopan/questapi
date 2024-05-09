package com.example.questapi.services;

import com.example.questapi.entities.Comment;
import com.example.questapi.repos.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
