package com.example.my_post_comment_user.comment.entity;


import com.example.my_post_comment_user.comment.dto.CommentRequest;
import com.example.my_post_comment_user.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private Long commentId;

    @Column
    private String content;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;



    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public void update(CommentRequest request) {
        this.content = request.getContent();
    }
}
