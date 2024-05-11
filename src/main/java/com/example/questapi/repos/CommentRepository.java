package com.example.questapi.repos;

import com.example.questapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findByPostIdAndUserId(Long postId, Long userId);

    List<Comment> findByPostId(Long postId);

    List<Comment> findByUserId(Long userId);
}
