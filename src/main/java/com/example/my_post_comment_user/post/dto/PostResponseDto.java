package com.example.assignment_week1.post.dto;

import com.example.assignment_week1.comment.entity.Comment;
import com.example.assignment_week1.comment.response.CommentResponse;
import com.example.assignment_week1.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class PostResponseDto {

    private String title;
    private String content;
    private String userId;
    private List<CommentResponse> commentResponseList = new ArrayList<>();

    public PostResponseDto(Post post)  {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUser().getUserId();

        for(Comment comment : post.getComments()){
            commentResponseList.add(new CommentResponse(comment));
        }
    }


}