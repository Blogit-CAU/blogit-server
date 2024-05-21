package com.blogit.blogitserver.controller;

import com.blogit.blogitserver.dto.request.ArticleRequest;
import com.blogit.blogitserver.dto.response.ArticleResponse;
import com.blogit.blogitserver.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Long> createArticle(@RequestParam Long userId, @Valid @RequestBody ArticleRequest request) {
        Long articleId = articleService.createArticle(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleId);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long articleId) {
        ArticleResponse article = articleService.getArticle(articleId);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ArticleResponse>> getArticlesByUser(@PathVariable Long userId) {
        List<ArticleResponse> articles = articleService.getArticlesByUser(userId);
        return ResponseEntity.ok(articles);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Void> updateArticle(@PathVariable Long articleId, @Valid @RequestBody ArticleRequest request) {
        articleService.updateArticle(articleId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }
}

