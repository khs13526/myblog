package com.sparta.myblog.domain;

import java.time.LocalDateTime;

public interface PostMapping {
    String getTitle();
    String getWriter();
    LocalDateTime getCreatedAt();
}
