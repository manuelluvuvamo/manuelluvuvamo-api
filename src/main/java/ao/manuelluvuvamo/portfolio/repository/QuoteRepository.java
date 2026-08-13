package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.Quote;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends StatusRepository<Quote> {
}
