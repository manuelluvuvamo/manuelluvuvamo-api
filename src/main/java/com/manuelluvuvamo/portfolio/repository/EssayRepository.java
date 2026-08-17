package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.Essay;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepository extends SlugRepository<Essay> {
}
