package com.example.questapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Comments")
@Data
public class Comment {
    @Id
    Long id;
    @Lob
    @Column(columnDefinition = "TEXT")
    String text;
    Long userId;
    Long postId;

}
