package com.example.questapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER) //  lazy when we don't want to load the user when we load the post
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // delete the post if the user is deleted
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    String text;
}
