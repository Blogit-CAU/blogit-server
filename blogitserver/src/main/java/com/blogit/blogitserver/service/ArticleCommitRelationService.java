package com.blogit.blogitserver.service;

import com.blogit.blogitserver.dto.ArticleCommitRelationDTO;
import com.blogit.blogitserver.entity.Article;
import com.blogit.blogitserver.entity.ArticleCommitRelation;
import com.blogit.blogitserver.entity.Commit;
import com.blogit.blogitserver.repository.ArticleCommitRelationRepository;
import com.blogit.blogitserver.repository.ArticleRepository;
import com.blogit.blogitserver.repository.CommitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleCommitRelationService {

    private final ArticleCommitRelationRepository relationRepository;
    private final ArticleRepository articleRepository;
    private final CommitRepository commitRepository;

    @Transactional
    public ArticleCommitRelation saveRelation(ArticleCommitRelationDTO relationDTO) {
        Article article = articleRepository.findById(relationDTO.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + relationDTO.getArticleId()));

        Commit commit = commitRepository.findById(relationDTO.getCommitId())
                .orElseThrow(() -> new EntityNotFoundException("Commit not found with id: " + relationDTO.getCommitId()));

        ArticleCommitRelation relation = ArticleCommitRelation.builder()
                .article(article)
                .commit(commit)
                .build();

        return relationRepository.save(relation);
    }

    public List<ArticleCommitRelation> getAllRelations() {
        return relationRepository.findAll();
    }

    public ArticleCommitRelation getRelationById(Long id) {
        return relationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relation not found with id: " + id));
    }
}

