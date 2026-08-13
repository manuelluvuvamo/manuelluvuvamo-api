package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.domain.SocialLink;
import ao.manuelluvuvamo.portfolio.repository.SocialLinkRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialLinkService extends CrudService<SocialLink> {

    private final SocialLinkRepository socialLinks;

    public SocialLinkService(SocialLinkRepository repository) {
        super(repository, "Rede social");
        this.socialLinks = repository;
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.asc("label"));
    }

    public List<SocialLink> findVisible() {
        return socialLinks.findByVisible(true, defaultSort());
    }
}
