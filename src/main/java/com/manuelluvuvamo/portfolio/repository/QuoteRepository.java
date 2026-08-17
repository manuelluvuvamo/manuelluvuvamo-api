package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.Quote;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends StatusRepository<Quote> {
}
