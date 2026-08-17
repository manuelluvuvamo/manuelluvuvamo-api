package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.common.ContentStatus;
import ao.manuelluvuvamo.portfolio.domain.Post;
import ao.manuelluvuvamo.portfolio.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostService extends SluggedContentService<Post> {

    /** Ritmo de leitura usado para estimar a duracao do artigo. */
    private static final int WORDS_PER_MINUTE = 200;

    private final MongoTemplate mongoTemplate;

    public PostService(PostRepository repository, MongoTemplate mongoTemplate) {
        super(repository, "Post");
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.desc("publishedAt"), Sort.Order.desc("createdAt"));
    }

    @Override
    protected void prepare(Post post) {
        super.prepare(post);

        // Publicar sem data definida carimba o momento da publicacao.
        if (post.getStatus() == ContentStatus.PUBLISHED && post.getPublishedAt() == null) {
            post.setPublishedAt(Instant.now());
        }
        if (post.getReadingMinutes() == null && post.getContent() != null) {
            post.setReadingMinutes(estimateReadingMinutes(post.getContent()));
        }
    }

    @Override
    protected void carryOver(Post existing, Post incoming) {
        // O formulario do dashboard nao tem estes campos; sem isto, gravar
        // uma alteracao ao texto poria os contadores a zero.
        incoming.setViewCount(existing.getViewCount());
        incoming.setReadCount(existing.getReadCount());
    }

    /** Uma abertura do artigo. */
    public void registerView(String slug) {
        increment(slug, "viewCount");
    }

    /** Uma leitura ate ao fim. */
    public void registerRead(String slug) {
        increment(slug, "readCount");
    }

    /**
     * Incrementa no proprio Mongo, sem ler o documento primeiro: duas visitas
     * simultaneas contam as duas, e o updatedAt do artigo nao e tocado.
     * Slug desconhecido nao e erro — nao ha nada para contar.
     */
    private void increment(String slug, String field) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("slug").is(slug).and("status").is(ContentStatus.PUBLISHED)),
                new Update().inc(field, 1),
                Post.class);
    }

    private int estimateReadingMinutes(String content) {
        int words = content.trim().isEmpty() ? 0 : content.trim().split("\\s+").length;
        return Math.max(1, (int) Math.ceil((double) words / WORDS_PER_MINUTE));
    }
}
