package com.example.my_post_comment_user.post.dto;


import com.example.my_post_comment_user.comment.entity.Comment;
import com.example.my_post_comment_user.comment.dto.CommentResponse;
import com.example.my_post_comment_user.post.entity.Post;
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