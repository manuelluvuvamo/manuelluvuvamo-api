package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.common.ContentStatus;
import ao.manuelluvuvamo.portfolio.domain.Post;
import ao.manuelluvuvamo.portfolio.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostService extends SluggedContentService<Post> {

    /** Ritmo de leitura usado para estimar a duracao do artigo. */
    private static final int WORDS_PER_MINUTE = 200;

    public PostService(PostRepository repository) {
        super(repository, "Post");
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

    private int estimateReadingMinutes(String content) {
        int words = content.trim().isEmpty() ? 0 : content.trim().split("\\s+").length;
        return Math.max(1, (int) Math.ceil((double) words / WORDS_PER_MINUTE));
    }
}
