package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import ao.manuelluvuvamo.portfolio.common.ContentStatus;
import ao.manuelluvuvamo.portfolio.common.Sluggable;
import ao.manuelluvuvamo.portfolio.common.Slugs;
import ao.manuelluvuvamo.portfolio.error.ApiException;
import ao.manuelluvuvamo.portfolio.repository.SlugRepository;

/**
 * CRUD de conteudo acessivel por slug. O slug e gerado a partir do titulo
 * quando vem vazio e recebe um sufixo numerico se ja estiver ocupado.
 */
public abstract class SluggedContentService<T extends BaseDocument & Sluggable> extends ContentService<T> {

    private final SlugRepository<T> slugRepository;

    protected SluggedContentService(SlugRepository<T> repository, String resourceName) {
        super(repository, resourceName);
        this.slugRepository = repository;
    }

    public T findPublishedBySlug(String slug) {
        return slugRepository.findBySlugAndStatus(slug, ContentStatus.PUBLISHED)
                .orElseThrow(() -> ApiException.notFound(resourceName(), slug));
    }

    public T findBySlug(String slug) {
        return slugRepository.findBySlug(slug)
                .orElseThrow(() -> ApiException.notFound(resourceName(), slug));
    }

    @Override
    protected void prepare(T entity) {
        String desired = Slugs.of(
                entity.getSlug() != null && !entity.getSlug().isBlank()
                        ? entity.getSlug()
                        : entity.slugSource());

        if (desired.isEmpty()) {
            throw ApiException.badRequest("Nao foi possivel gerar um slug: titulo vazio.");
        }
        entity.setSlug(uniqueSlug(desired, entity.getId()));
    }

    private String uniqueSlug(String base, String currentId) {
        String candidate = base;
        int suffix = 2;
        while (isTaken(candidate, currentId)) {
            candidate = base + "-" + suffix++;
        }
        return candidate;
    }

    private boolean isTaken(String slug, String currentId) {
        return slugRepository.findBySlug(slug)
                .map(found -> !found.getId().equals(currentId))
                .orElse(false);
    }
}
