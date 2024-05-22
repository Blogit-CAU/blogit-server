package com.blogit.blogitserver.repository;

import com.blogit.blogitserver.entity.ArticleCommitRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommitRelationRepository extends JpaRepository<ArticleCommitRelation, Long> {
}
