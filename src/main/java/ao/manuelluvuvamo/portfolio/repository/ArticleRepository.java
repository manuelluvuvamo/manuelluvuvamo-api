package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends SlugRepository<Article> {
}
