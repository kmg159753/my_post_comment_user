package com.example.assignment_week1.post.dto;

import com.example.assignment_week1.auth.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private User user;
}