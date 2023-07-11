package com.example.my_post_comment_user.post.dto;


import com.example.my_post_comment_user.auth.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private User user;
}