package com.example.my_post_comment_user.global.exception;


import com.example.my_post_comment_user.global.type.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;

}
