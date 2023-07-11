package com.example.my_post_comment_user.post.service;


import com.example.my_post_comment_user.auth.entity.UserRoleEnum;
import com.example.my_post_comment_user.auth.entity.User;
import com.example.my_post_comment_user.auth.repository.UserRepository;
import com.example.my_post_comment_user.global.exception.CustomException;
import com.example.my_post_comment_user.global.type.ErrorCode;
import com.example.my_post_comment_user.global.util.JwtUtil;
import com.example.my_post_comment_user.post.dto.PostRequestDto;
import com.example.my_post_comment_user.post.dto.PostResponseDto;
import com.example.my_post_comment_user.post.entity.Post;
import com.example.my_post_comment_user.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {

        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(factor -> {
            return new PostResponseDto(factor);
        }).toList();
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_POST));

        return new PostResponseDto(post);
    }


    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {

        Post savedPost = postRepository.save(
                new Post( requestDto, user)
        );

        return new PostResponseDto(savedPost);
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_POST));

        if(UserRoleEnum.ADMIN.toString().equals(user.getRole())){//관리자라면
            post.update(requestDto);
        }else{
            if(post.getUser().getUserId().equals(user.getUserId())){
                post.update(requestDto);
            }else{
                throw new CustomException(ErrorCode.NOT_AUTHORITY);
            }
        }
        return new PostResponseDto(post);
    }

    @Transactional
    public String deletePost(Long id, User user) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_POST));

        String title = post.getTitle();

        if(UserRoleEnum.ADMIN.toString().equals(user.getRole())){//관리자라면
            postRepository.delete(post);
        }else{
            if(post.getUser().getUserId().equals(user.getUserId())){
                postRepository.delete(post);
            }else{
                throw new CustomException(ErrorCode.NOT_AUTHORITY);
            }
        }

        return title;
    }


}