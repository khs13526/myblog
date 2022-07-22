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
            postRepository.save(new Post("프론트엔드의 꽃, 리액트", "임민영", "행복한 날이었다.", "1234"));

            System.out.println("데이터 인쇄");
//            List<Post> postList = postRepository.findAll();
//            for (int i=0; i<postList.size(); i++) {
//                Post post = postList.get(i);
//                System.out.println(post.getId());
//                System.out.println(post.getTitle());
//                System.out.println(post.getWriter());
//                System.out.println(post.getContent());
//                System.out.println(post.getPassword());
//            }
        };
    }
}
