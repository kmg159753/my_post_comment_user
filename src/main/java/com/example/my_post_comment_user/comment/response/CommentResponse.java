package com.example.assignment_week1.comment.response;

import com.example.assignment_week1.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    String content;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
    }
}
