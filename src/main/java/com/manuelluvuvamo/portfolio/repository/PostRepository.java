package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends SlugRepository<Post> {
}
