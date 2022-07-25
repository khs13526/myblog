package com.sparta.myblog.service;

import com.sparta.myblog.AESUtil.AES256;
import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostDetailMapping;
import com.sparta.myblog.domain.PostRepository;
import com.sparta.myblog.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public List<PostDetailMapping> update(Long id, PostRequestDto requestDto) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        post1.update(requestDto);
        return postRepository.findPostDetailById(id);
    }

    public boolean checkPassword(Long id, String password) throws Exception {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        AES256 aes256 = new AES256();
        return post1.getPassword().equals(aes256.encrypt(password));
    }

    public Long encryptPassword(PostRequestDto requestDto) throws Exception {
        AES256 aes256 = new AES256();
        requestDto.setPassword(aes256.encrypt(requestDto.getPassword()));
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post.getId();
    }
}
