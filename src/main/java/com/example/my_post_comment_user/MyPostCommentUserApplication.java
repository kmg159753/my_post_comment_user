package com.example.my_post_comment_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyPostCommentUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPostCommentUserApplication.class, args);
	}

}
