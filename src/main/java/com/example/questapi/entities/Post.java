package com.example.questapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Posts")
@Data
public class Post {
    @Id
    Long id;
    Long userId;
    String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    String text;
}
