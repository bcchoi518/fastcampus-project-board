package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비지니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        // Given
        long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "hashtag"))
        );

        // When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);

        // Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);

    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Cbc", LocalDateTime.now(), "Cbc", "content"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }

    @DisplayName("댓글의 ID와 수정 정보를 입력하면, 댓글을 수정한다.")
    @Test
    void givenArticleCommentIdAndModifiedArticleCommentInfo_whenUpdatingArticleComment_thenUpdatesArticleComment() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.updateArticleComment("content");

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }

    @DisplayName("댓글의 ID를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenArticleCommentId_whenDeletingArticleComment_thenDeletesArticleComment() {
        // Given
        willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class));

        // When
        sut.deleteArticleComment(1L);

        // Then
        then(articleCommentRepository).should().delete(any(ArticleComment.class));

    }
}