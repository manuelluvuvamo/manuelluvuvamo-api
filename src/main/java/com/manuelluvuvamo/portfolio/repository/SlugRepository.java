package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.common.ContentStatus;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/** Base para coleccoes com estado de publicacao e slug unico. */
@NoRepositoryBean
public interface SlugRepository<T> extends StatusRepository<T> {

    Optional<T> findBySlug(String slug);

    Optional<T> findBySlugAndStatus(String slug, ContentStatus status);

    boolean existsBySlug(String slug);
}
