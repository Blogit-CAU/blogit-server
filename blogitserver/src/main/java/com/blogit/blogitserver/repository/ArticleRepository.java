package com.blogit.blogitserver.repository;

import com.blogit.blogitserver.entity.Article;
import com.blogit.blogitserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByMember(Member member);
}
