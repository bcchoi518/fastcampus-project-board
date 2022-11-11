package com.fastcampus.projectboard.dto;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.Article} entity
 */
public record ArticleUpdateDto(
        String title,
        String content,
        String hashtag
) {
    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}