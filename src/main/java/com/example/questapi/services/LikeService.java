package com.example.questapi.services;

import com.example.questapi.repos.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
}
