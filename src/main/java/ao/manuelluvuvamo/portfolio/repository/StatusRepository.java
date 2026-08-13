package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.common.ContentStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/** Base para coleccoes com estado de publicacao. */
@NoRepositoryBean
public interface StatusRepository<T> extends MongoRepository<T, String> {

    List<T> findByStatus(ContentStatus status, Sort sort);
}
