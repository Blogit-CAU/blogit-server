package com.blogit.blogitserver.repository;

import com.blogit.blogitserver.entity.Article;
import com.blogit.blogitserver.entity.ArticleCommitRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommitRelationRepository extends JpaRepository<ArticleCommitRelation, Long> {
    List<ArticleCommitRelation> findByArticleIn(List<Article> articles);
}
