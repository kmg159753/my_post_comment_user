package com.example.my_post_comment_user.comment.dto;


import com.example.my_post_comment_user.comment.entity.Comment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponse {
    String content;

    List<CommentResponse> commentResponseList = new ArrayList<>();

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();

        for(Comment comment_comment : comment.getCommentList()){
            commentResponseList.add(new CommentResponse(comment_comment));
        }
    }
}
