package com.sparta.myblog.controller;

import com.sparta.myblog.apiResponse.ApiResult;
import com.sparta.myblog.apiResponse.ApiUtils;
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

    @PostMapping("/api/post")
    public ApiResult<List<PostDetailMapping>> createPost(@RequestBody PostRequestDto requestDto) throws Exception {
        return ApiUtils.success(postRepository.findPostDetailById(postService.encryptPassword(requestDto)));
    }

    @GetMapping("/api/post")
    public ApiResult<List<com.sparta.myblog.domain.PostMapping>> getPosts() {
        return ApiUtils.success(postRepository.findAllBy(Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    @GetMapping("/api/post/{id}")
    public ApiResult<List<PostDetailMapping>> getPostById(@PathVariable Long id) {
        return ApiUtils.success(postRepository.findPostDetailById(id));
    }

    @GetMapping("/api/post/password/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestParam("password") String password) throws Exception {
        return postService.checkPassword(id, password);
    }


    @PutMapping("/api/post/{id}")
    public ApiResult<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @RequestParam("password") String password) throws Exception {
        if(postService.checkPassword(id, password)){
            return ApiUtils.success(postService.update(id, requestDto));
        } else {
            return ApiUtils.error("error",400);
        }
    }

    @DeleteMapping("/api/post/{id}")
    public ApiResult<?> deletePost(@PathVariable Long id, @RequestParam("password") String password) throws Exception {
        if(postService.checkPassword(id, password)){
            postRepository.deleteById(id);
            return ApiUtils.success(true);
        }  else {
            return ApiUtils.error("error", 400);
        }
    }
}
