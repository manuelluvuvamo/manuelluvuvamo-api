package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.domain.Project;
import ao.manuelluvuvamo.portfolio.repository.ProjectRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends SluggedContentService<Project> {

    public ProjectService(ProjectRepository repository) {
        super(repository, "Projecto");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.desc("year"), Sort.Order.asc("title"));
    }
}
