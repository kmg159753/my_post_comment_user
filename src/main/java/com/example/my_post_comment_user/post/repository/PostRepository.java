package com.example.my_post_comment_user.post.repository;


import com.example.my_post_comment_user.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
//    List<Post> findAllByOrderByCreated_atDesc();
    List<Post> findAllByOrderByCreatedAtDesc();
}
