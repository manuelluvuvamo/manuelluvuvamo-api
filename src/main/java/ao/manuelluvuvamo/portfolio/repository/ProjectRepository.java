package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends SlugRepository<Project> {
}
