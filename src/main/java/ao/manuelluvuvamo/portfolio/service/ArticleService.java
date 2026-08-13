package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.common.ContentStatus;
import ao.manuelluvuvamo.portfolio.domain.Article;
import ao.manuelluvuvamo.portfolio.error.ApiException;
import ao.manuelluvuvamo.portfolio.repository.ArticleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ArticleService extends SluggedContentService<Article> {

    public ArticleService(ArticleRepository repository) {
        super(repository, "Artigo");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.desc("publishedAt"), Sort.Order.desc("createdAt"));
    }

    @Override
    protected void prepare(Article article) {
        super.prepare(article);

        boolean hasBody = article.getContent() != null && !article.getContent().isBlank();
        boolean hasLink = article.getExternalUrl() != null && !article.getExternalUrl().isBlank();
        if (!hasBody && !hasLink) {
            throw ApiException.badRequest("Um artigo precisa de conteudo proprio ou de uma ligacao externa.");
        }
        if (article.getStatus() == ContentStatus.PUBLISHED && article.getPublishedAt() == null) {
            article.setPublishedAt(Instant.now());
        }
    }
}
