package com.example.assignment_week1.global.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Password
    NOT_AUTHORITY(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다."),
    NO_POST(HttpStatus.BAD_REQUEST, "잘못된 게시글 정보입니다."),

    //Login / Register
    LOGIN_NO(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    NAME_SAME(HttpStatus.BAD_REQUEST, "중복된 username 입니다."),

    //Token
    WRONG_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),

    //댓글
    WRONG_BOARD_PID(HttpStatus.BAD_REQUEST, "잘못된 게시글 접근 입니다."),
    WRONG_COMMENT_PID(HttpStatus.BAD_REQUEST, "잘못된 댓글 접근 입니다."),
    WRONG_NAME(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
