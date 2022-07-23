package com.sparta.myblog.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
public interface PostDetailMapping {
    Long getId();
    String getTitle();
    String getWriter();
    String getContent();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
}
