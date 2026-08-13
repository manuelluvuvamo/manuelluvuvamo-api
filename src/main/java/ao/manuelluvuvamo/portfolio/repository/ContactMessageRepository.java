package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.ContactMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends MongoRepository<ContactMessage, String> {

    List<ContactMessage> findByArchived(boolean archived, Sort sort);

    long countByReadFalseAndArchivedFalse();
}
