package com.example.my_post_comment_user.comment.service;

import com.example.my_post_comment_user.auth.entity.UserRoleEnum;
import com.example.my_post_comment_user.auth.entity.User;
import com.example.my_post_comment_user.comment.entity.Comment;
import com.example.my_post_comment_user.comment.repository.CommentRepository;
import com.example.my_post_comment_user.comment.dto.CommentRequest;
import com.example.my_post_comment_user.comment.dto.CommentResponse;
import com.example.my_post_comment_user.global.exception.CustomException;
import com.example.my_post_comment_user.global.type.ErrorCode;
import com.example.my_post_comment_user.post.entity.Post;
import com.example.my_post_comment_user.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse createComment(CommentRequest commentRequest, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
        {
            throw new CustomException(ErrorCode.NO_POST);
        });

        Comment savedComment = commentRepository.save(new Comment(commentRequest, post));

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


    public CommentResponse createComment_Comment(CommentRequest commentRequest, Long commentId, User user) {
        Comment parentcomment = commentRepository.findById(commentId).orElseThrow(() ->
                new CustomException(ErrorCode.WRONG_COMMENT_ID)
        );

        Comment comment_comment = new Comment(commentRequest, parentcomment);

        commentRepository.save(comment_comment);

        return new CommentResponse(comment_comment);
    }
}
