package com.sparta.myblog;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
@EnableJpaAuditing
@SpringBootApplication
public class MyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PostRepository postRepository, PostService postService) {
        return (args) -> {
            postRepository.save(new Post("오늘은 jpa에 대해서 배웠다.", "김현서", "매우 재미있었다.", "1234"));
            System.out.println("데이터 인쇄");
        };
    }
}
