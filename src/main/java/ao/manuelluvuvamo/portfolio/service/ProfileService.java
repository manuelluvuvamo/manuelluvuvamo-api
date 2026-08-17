package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.domain.Profile;
import ao.manuelluvuvamo.portfolio.repository.ProfileRepository;
import org.springframework.stereotype.Service;

/**
 * O perfil e um documento unico. Se ainda nao existir, e criado vazio para
 * que o dashboard tenha sempre um formulario para preencher.
 */
@Service
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public Profile get() {
        return repository.findByKey(Profile.SINGLETON_KEY).orElseGet(this::createEmpty);
    }

    public Profile update(Profile incoming) {
        Profile current = get();
        incoming.setId(current.getId());
        incoming.setCreatedAt(current.getCreatedAt());
        incoming.setKey(Profile.SINGLETON_KEY);
        return repository.save(incoming);
    }

    private Profile createEmpty() {
        Profile profile = new Profile();
        profile.setKey(Profile.SINGLETON_KEY);
        profile.setFullName("Manuel Luvuvamo");
        return repository.save(profile);
    }
}
