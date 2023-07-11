package com.example.my_post_comment_user.auth.controller;


import com.example.my_post_comment_user.auth.dto.SignupRequestDto;
import com.example.my_post_comment_user.auth.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final RegisterService userService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequestDto requestDto){

        String username = userService.signup(requestDto);

        return username + " 가 회원가입에 성공했습니다.";

    }
}
