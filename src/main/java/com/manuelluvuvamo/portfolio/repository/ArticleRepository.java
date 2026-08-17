package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends SlugRepository<Article> {
}
