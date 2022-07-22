package com.sparta.myblog.controller;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.domain.PostRequestDto;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/posts")
    public Post createCourse(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/courses")
    public List<com.sparta.myblog.domain.PostMapping> getCourses() {
        return postRepository.findAllBy();
    }


    @PutMapping("/api/courses/{id}")
    public Long updateCourse(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/courses/{id}")
    public Long deleteCourse(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
