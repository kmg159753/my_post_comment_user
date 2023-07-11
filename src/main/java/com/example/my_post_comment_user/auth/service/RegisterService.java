package com.example.my_post_comment_user.auth.service;


import com.example.my_post_comment_user.auth.config.PasswordConfig;
import com.example.my_post_comment_user.auth.dto.SignupRequestDto;
import com.example.my_post_comment_user.auth.entity.UserRoleEnum;
import com.example.my_post_comment_user.auth.entity.User;
import com.example.my_post_comment_user.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class RegisterService {

        private final UserRepository userRepository;
        private final PasswordConfig passwordConfig;



        public String signup(SignupRequestDto requestDto) {
            Optional<User> user = userRepository.findByUserId(requestDto.getUserId());

            if (user.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            }

            if (!requestDto.isAdmin()) {
                User saveuser = new User(requestDto.getUserId(), passwordConfig.passwordEncoder().encode(requestDto.getPassword()), UserRoleEnum.USER);
                userRepository.save(saveuser);
            }else{
                User saveuser = new User(requestDto.getUserId(), passwordConfig.passwordEncoder().encode(requestDto.getPassword()), UserRoleEnum.ADMIN);
                userRepository.save(saveuser);
            }

            return requestDto.getUserId();
        }

    }