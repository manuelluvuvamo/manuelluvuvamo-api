package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.Essay;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepository extends SlugRepository<Essay> {
}
