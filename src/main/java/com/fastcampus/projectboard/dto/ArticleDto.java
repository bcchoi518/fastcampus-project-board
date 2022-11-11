package com.fastcampus.projectboard.dto;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.Article} entity
 */
public record ArticleDto(
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hashtag
) {
    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hashtag);
    }
}