package com.sparta.myblog.service;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository courseRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post1 = courseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        post1.update(requestDto);
        return post1.getId();
    }
}
