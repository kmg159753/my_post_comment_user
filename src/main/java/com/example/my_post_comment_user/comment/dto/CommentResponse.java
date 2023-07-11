package com.example.my_post_comment_user.comment.dto;


import com.example.my_post_comment_user.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    String content;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
    }
}
