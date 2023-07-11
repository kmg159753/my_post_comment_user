package com.example.assignment_week1.comment.service;

import com.example.assignment_week1.auth.dto.type.UserRoleEnum;
import com.example.assignment_week1.auth.entity.User;
import com.example.assignment_week1.auth.repository.UserRepository;
import com.example.assignment_week1.comment.entity.Comment;
import com.example.assignment_week1.comment.repository.CommentRepository;
import com.example.assignment_week1.comment.request.CommentRequest;
import com.example.assignment_week1.comment.response.CommentResponse;
import com.example.assignment_week1.global.exception.CustomException;
import com.example.assignment_week1.global.type.ErrorCode;
import com.example.assignment_week1.global.util.JwtUtil;
import com.example.assignment_week1.post.entity.Post;
import com.example.assignment_week1.post.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse createComment(CommentRequest commentRequest, Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
        {
            throw new CustomException(ErrorCode.NO_POST);
        });

        Comment savedComment = commentRepository.save(new Comment(commentRequest.getContent(), post));

        return new CommentResponse(savedComment);
    }

    @Transactional
    public CommentResponse updateComment(CommentRequest commentRequest, Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if(UserRoleEnum.ADMIN.toString().equals(user.getRole())){//관리자라면
            comment.update(commentRequest);
        }else{

            if(comment.getPost().getUser().getUserId().equals(user.getUserId())){
                comment.update(commentRequest);
            }else{
                throw new CustomException(ErrorCode.NOT_AUTHORITY);
            }
        }

        return new CommentResponse(comment);
    }

    @Transactional
    public Long deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if(UserRoleEnum.ADMIN.toString().equals(user.getRole())){//관리자라면
            commentRepository.delete(comment);
        }else{
            if(comment.getPost().getUser().getUserId().equals(user.getUserId())){
                commentRepository.delete(comment);
            }else{
                throw new CustomException(ErrorCode.NOT_AUTHORITY);
            }
        }

        return comment.getCommentId();
    }




}
