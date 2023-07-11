package com.example.my_post_comment_user.comment.repository;


import com.example.my_post_comment_user.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
