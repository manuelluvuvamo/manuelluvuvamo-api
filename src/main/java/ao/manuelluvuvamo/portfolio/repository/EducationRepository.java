package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.Education;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends MongoRepository<Education, String> {
}
