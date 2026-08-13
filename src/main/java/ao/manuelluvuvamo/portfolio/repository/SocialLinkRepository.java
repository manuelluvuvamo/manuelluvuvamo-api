package ao.manuelluvuvamo.portfolio.repository;

import ao.manuelluvuvamo.portfolio.domain.SocialLink;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialLinkRepository extends MongoRepository<SocialLink, String> {

    List<SocialLink> findByVisible(boolean visible, Sort sort);
}
