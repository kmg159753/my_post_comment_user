package com.example.assignment_week1.comment.repository;

import com.example.assignment_week1.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Long> {

}
