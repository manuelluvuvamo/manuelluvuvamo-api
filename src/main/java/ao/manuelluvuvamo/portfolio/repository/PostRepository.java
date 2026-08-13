package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends SlugRepository<Post> {
}
