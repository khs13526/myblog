package com.sparta.myblog.controller;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostDetailMapping;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.domain.PostRequestDto;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        return postRepository.findAllBy(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @GetMapping("/api/courses/{id}")
    public List<PostDetailMapping> getCourseById(@PathVariable Long id) {
        return postRepository.findPostDetailById(id);
    }

    @GetMapping("/api/courses/{id}/password")
    public boolean checkPassword(@PathVariable Long id, @RequestParam("password") String password) {
        return postService.checkPassword(id, password);
    }


    @PutMapping("/api/courses/{id}")
    public String updateCourse(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @RequestParam("password") String password) {
        if(postService.checkPassword(id, password)){
            return postService.update(id, requestDto);
        } else {
            return "비밀번호 확인";
        }
    }

    @DeleteMapping("/api/courses/{id}")
    public String deleteCourse(@PathVariable Long id, @RequestParam("password") String password) {
        if(postService.checkPassword(id, password)){
            postRepository.deleteById(id);
            return "삭제 완료";
        }  else {
            return "비밀번호 확인";
        }
    }
}
