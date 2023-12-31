package com.example.my_post_comment_user.comment.controller;


import com.example.my_post_comment_user.auth.UserDetailsImpl;
import com.example.my_post_comment_user.comment.dto.CommentRequest;
import com.example.my_post_comment_user.comment.dto.CommentResponse;
import com.example.my_post_comment_user.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("{postId}")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest,
                                                @PathVariable("postId") Long postId
                                                ) {
        CommentResponse commentResponse = commentService.createComment(commentRequest, postId);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PostMapping("/reply/{commentId}")
    public ResponseEntity<CommentResponse> createComment_Comment(@RequestBody CommentRequest commentRequest,
                                                @PathVariable("commentId") Long commentId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponse commentResponse = commentService.createComment_Comment(commentRequest, commentId, userDetails.getUser());
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentResponse> updateComment(@RequestBody CommentRequest commentRequest,
                                                @PathVariable("id") Long commentId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponse commentResponse = commentService.updateComment(commentRequest, commentId, userDetails.getUser());
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long Id = commentService.deleteComment(commentId, userDetails.getUser());

        return ResponseEntity.ok("댓글 아이디 : " + Id + " 댓글이 삭제 되었습니다.");
    }
}
