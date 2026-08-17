package com.manuelluvuvamo.portfolio.bootstrap;

import com.manuelluvuvamo.portfolio.config.SeedProperties;
import com.manuelluvuvamo.portfolio.domain.*;
import com.manuelluvuvamo.portfolio.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Popula o Mongo a partir de classpath:seed/portfolio-seed.json.
 *
 * <p>Cada coleccao so e semeada se estiver vazia, por isso correr a aplicacao
 * varias vezes nao duplica nada nem sobrepoe o que ja editaste no dashboard.</p>
 */
@Component
@Order(10)
public class ContentSeeder implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(ContentSeeder.class);
    private static final String SEED_FILE = "seed/portfolio-seed.json";

    private final SeedProperties properties;
    private final ObjectMapper objectMapper;

    private final ProfileRepository profiles;
    private final SocialLinkRepository socialLinks;
    private final ExperienceRepository experiences;
    private final EducationRepository education;
    private final CertificationRepository certifications;
    private final SkillRepository skills;
    private final ProjectRepository projects;
    private final PostRepository posts;
    private final ArticleRepository articles;
    private final VlogRepository vlogs;
    private final EssayRepository essays;
    private final QuoteRepository quotes;

    public ContentSeeder(SeedProperties properties, ObjectMapper objectMapper,
                         ProfileRepository profiles, SocialLinkRepository socialLinks,
                         ExperienceRepository experiences, EducationRepository education,
                         CertificationRepository certifications, SkillRepository skills,
                         ProjectRepository projects, PostRepository posts, ArticleRepository articles,
                         VlogRepository vlogs, EssayRepository essays, QuoteRepository quotes) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.profiles = profiles;
        this.socialLinks = socialLinks;
        this.experiences = experiences;
        this.education = education;
        this.certifications = certifications;
        this.skills = skills;
        this.projects = projects;
        this.posts = posts;
        this.articles = articles;
        this.vlogs = vlogs;
        this.essays = essays;
        this.quotes = quotes;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        if (!properties.enabled()) {
            return;
        }

        ClassPathResource resource = new ClassPathResource(SEED_FILE);
        if (!resource.exists()) {
            log.warn("Seed activo mas {} nao foi encontrado. Nada a fazer.", SEED_FILE);
            return;
        }

        JsonNode root;
        try (InputStream stream = resource.getInputStream()) {
            root = objectMapper.readTree(stream);
        }

        seedProfile(root.get("profile"));
        seed(root, "socialLinks", SocialLink.class, socialLinks);
        seed(root, "experiences", Experience.class, experiences);
        seed(root, "education", Education.class, education);
        seed(root, "certifications", Certification.class, certifications);
        seed(root, "skills", Skill.class, skills);
        seed(root, "projects", Project.class, projects);
        seed(root, "posts", Post.class, posts);
        seed(root, "articles", Article.class, articles);
        seed(root, "vlogs", Vlog.class, vlogs);
        seed(root, "essays", Essay.class, essays);
        seed(root, "quotes", Quote.class, quotes);
    }

    private void seedProfile(JsonNode node) throws IOException {
        if (node == null || node.isNull() || profiles.findByKey(Profile.SINGLETON_KEY).isPresent()) {
            return;
        }
        Profile profile = objectMapper.treeToValue(node, Profile.class);
        profile.setKey(Profile.SINGLETON_KEY);
        profiles.save(profile);
        log.info("Seed: perfil criado.");
    }

    private <T> void seed(JsonNode root, String field, Class<T> type, MongoRepository<T, String> repository)
            throws IOException {

        JsonNode node = root.get(field);
        if (node == null || !node.isArray() || node.isEmpty() || repository.count() > 0) {
            return;
        }

        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
        List<T> items = objectMapper.readValue(objectMapper.treeAsTokens(node), listType);
        repository.saveAll(items);
        log.info("Seed: {} registos em {}.", items.size(), field);
    }
}
