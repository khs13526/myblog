package com.sparta.myblog.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.myblog.apiResponse.ApiResult;
import com.sparta.myblog.apiResponse.ApiUtils;
import com.sparta.myblog.domain.PostDetailMapping;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.domain.PostRequestDto;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/post")
    public ApiResult<List<PostDetailMapping>> createPost(@RequestBody PostRequestDto requestDto) throws Exception {
        return ApiUtils.success(postRepository.findPostDetailById(postService.save(requestDto)));
    }

    @GetMapping("/post")
    public ApiResult<List<com.sparta.myblog.domain.PostMapping>> getPosts() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        log.info(mapper.writeValueAsString(postRepository.findAllBy(Sort.by(Sort.Direction.DESC, "createdAt"))));
        log.info(postRepository.findAllBy(Sort.by(Sort.Direction.DESC, "createdAt")).toString());
        return ApiUtils.success(postRepository.findAllBy(Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    @GetMapping("/post/{id}")
    public ApiResult<List<PostDetailMapping>> getPostById(@PathVariable Long id) {
        return ApiUtils.success(postRepository.findPostDetailById(id));
    }

    @GetMapping("/post/password/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestParam("password") String password) throws Exception {
        return postService.checkPassword(id, password);
    }

    @PutMapping("/post/{id}")
    public ApiResult<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @RequestParam("password") String password) throws Exception {
        if(postService.checkPassword(id, password)){
            return ApiUtils.success(postService.update(id, requestDto));
        } else {
            return ApiUtils.error("error",400);
        }
    }

    @DeleteMapping("/post/{id}")
    public ApiResult<?> deletePost(@PathVariable Long id, @RequestParam("password") String password) throws Exception {
        if(postService.checkPassword(id, password)){
            postRepository.deleteById(id);
            return ApiUtils.success(true);
        }  else {
            return ApiUtils.error("error", 400);
        }
    }
}
