package com.example.assignment_week1.post.repository;

import com.example.assignment_week1.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
//    List<Post> findAllByOrderByCreated_atDesc();
    List<Post> findAllByOrderByCreatedAtDesc();
}
