package com.example.my_post_comment_user.auth.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @Size(min = 4, max = 10)
    @NotNull
    @Pattern(regexp = "^[a-z0-9]+$")
    @NotBlank
    private String userId;

    @Size(min = 8, max = 15)
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=]).*$")
    @NotBlank
    private String password;

    @NotNull
    private boolean admin;

}