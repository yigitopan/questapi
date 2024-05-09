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
    Long id;
    @ManyToOne(fetch = FetchType.LAZY) // eager to get the user object too
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // delete the post if the user is deleted
    @JsonIgnore
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    String text;
}
