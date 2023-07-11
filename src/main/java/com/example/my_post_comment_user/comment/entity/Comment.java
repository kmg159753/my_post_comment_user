package com.example.my_post_comment_user.comment.entity;


import com.example.my_post_comment_user.comment.dto.CommentRequest;
import com.example.my_post_comment_user.global.entity.Timestamped;
import com.example.my_post_comment_user.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private Long commentId;

    @Column
    private String content;


    //셀프 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Comment parent;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;



    public Comment(CommentRequest request, Post post) {
        this.content = request.getContent();
        this.post = post;
    }
    public Comment(CommentRequest request,Comment parentcomment) {
        this.content = request.getContent();
        this.parent = parentcomment;
        this.post = parentcomment.getPost();
    }

    public void update(CommentRequest request) {
        this.content = request.getContent();
    }
}
