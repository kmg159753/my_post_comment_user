package com.example.assignment_week1.post.controller;


import com.example.assignment_week1.auth.UserDetailsImpl;
import com.example.assignment_week1.post.dto.PostRequestDto;
import com.example.assignment_week1.post.dto.PostResponseDto;
import com.example.assignment_week1.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @GetMapping()
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> responseDtoList = postService.getAllPosts();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // PostRequestDto를 사용하여 게시글을 생성하고, 생성된 게시글의 정보를 PostResponseDto에 담아 반환합니다.

        try{
            PostResponseDto responseDto = postService.createPost(requestDto, userDetails.getUser());

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        // id를 사용하여 해당 게시글의 정보를 조회하고, 조회된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        PostResponseDto responseDto = postService.getPost(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // id와 수정할 데이터를 사용하여 게시글을 수정하고, 수정된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        PostResponseDto responseDto = postService.updatePost(id, requestDto, userDetails.getUser());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // id와 비밀번호를 사용하여 게시글을 삭제합니다.
        String title = postService.deletePost(id, userDetails.getUser());
        return ResponseEntity.ok(title + "게시글이 삭제 되었습니다. .");
    }
}