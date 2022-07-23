package com.sparta.myblog.domain;

import java.time.LocalDateTime;

public interface PostMapping {
    Long getId();
    String getTitle();
    String getWriter();
    LocalDateTime getCreatedAt();
}
