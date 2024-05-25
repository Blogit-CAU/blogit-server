package com.blogit.blogitserver.service;

import com.blogit.blogitserver.dto.request.ArticleRequest;
import com.blogit.blogitserver.dto.response.ArticleResponse;
import com.blogit.blogitserver.entity.Article;
import com.blogit.blogitserver.entity.ArticleCommitRelation;
import com.blogit.blogitserver.entity.Commit;
import com.blogit.blogitserver.entity.Member;
import com.blogit.blogitserver.repository.ArticleCommitRelationRepository;
import com.blogit.blogitserver.repository.ArticleRepository;
import com.blogit.blogitserver.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ArticleCommitRelationRepository relationRepository;

    @Transactional
    public Long createArticle(Long userId, ArticleRequest request) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

        Article article = Article.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return articleRepository.save(article).getArticleId();
    }

    public ArticleResponse getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("해당 글을 찾을 수 없습니다."));
        return new ArticleResponse(article);
    }

    public List<ArticleResponse> getArticlesByUser(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
        List<Article> articles = articleRepository.findByMember(member);
        return articles.stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());
    }

    public List<Commit> getCommitsByUser(Long userId) {
        Optional<Member> userOpt = memberRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }

        Member member = userOpt.get();
        List<Article> articles = articleRepository.findByMember(member);
        List<ArticleCommitRelation> relations = relationRepository.findByArticleIn(articles);

        return relations.stream()
                .map(ArticleCommitRelation::getCommit)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleRequest request) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("해당 글을 찾을 수 없습니다."));

        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}

