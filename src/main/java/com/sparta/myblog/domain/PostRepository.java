package com.sparta.myblog.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostDetailMapping> findAllBy(Sort createdAt);
    List<PostDetailMapping> findPostDetailById(Long id);
}
