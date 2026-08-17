package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends SlugRepository<Project> {
}
