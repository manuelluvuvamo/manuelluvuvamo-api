package ao.manuelluvuvamo.portfolio.web;

import ao.manuelluvuvamo.portfolio.domain.*;
import ao.manuelluvuvamo.portfolio.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tudo o que o portfolio publico precisa de ler. So devolve conteudo
 * publicado; rascunhos e arquivo ficam no dashboard.
 */
@RestController
@RequestMapping("/api/v1/public")
@Tag(name = "Publico")
public class PublicController {

    private final ProfileService profile;
    private final ProjectService projects;
    private final PostService posts;
    private final ArticleService articles;
    private final VlogService vlogs;
    private final EssayService essays;
    private final QuoteService quotes;
    private final ExperienceService experiences;
    private final EducationService education;
    private final CertificationService certifications;
    private final SkillService skills;
    private final SocialLinkService socialLinks;
    private final ContactMessageService messages;

    public PublicController(ProfileService profile, ProjectService projects, PostService posts,
                            ArticleService articles, VlogService vlogs, EssayService essays,
                            QuoteService quotes, ExperienceService experiences, EducationService education,
                            CertificationService certifications, SkillService skills,
                            SocialLinkService socialLinks, ContactMessageService messages) {
        this.profile = profile;
        this.projects = projects;
        this.posts = posts;
        this.articles = articles;
        this.vlogs = vlogs;
        this.essays = essays;
        this.quotes = quotes;
        this.experiences = experiences;
        this.education = education;
        this.certifications = certifications;
        this.skills = skills;
        this.socialLinks = socialLinks;
        this.messages = messages;
    }

    /**
     * Todo o conteudo do site num unico pedido. E o que o Next.js usa para
     * gerar as paginas, evitando uma cascata de chamadas por rota.
     */
    @GetMapping("/bootstrap")
    public Map<String, Object> bootstrap() {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("profile", profile.get());
        payload.put("socialLinks", socialLinks.findVisible());
        payload.put("projects", projects.findPublished());
        payload.put("posts", posts.findPublished());
        payload.put("articles", articles.findPublished());
        payload.put("vlogs", vlogs.findPublished());
        payload.put("essays", essays.findPublished());
        payload.put("quotes", quotes.findPublished());
        payload.put("experiences", experiences.findAll());
        payload.put("education", education.findAll());
        payload.put("certifications", certifications.findAll());
        payload.put("skills", skills.findAll());
        return payload;
    }

    @GetMapping("/profile")
    public Profile profile() {
        return profile.get();
    }

    @GetMapping("/social-links")
    public List<SocialLink> socialLinks() {
        return socialLinks.findVisible();
    }

    @GetMapping("/projects")
    public List<Project> projects() {
        return projects.findPublished();
    }

    @GetMapping("/projects/{slug}")
    public Project project(@PathVariable String slug) {
        return projects.findPublishedBySlug(slug);
    }

    @GetMapping("/posts")
    public List<Post> posts() {
        return posts.findPublished();
    }

    @GetMapping("/posts/{slug}")
    public Post post(@PathVariable String slug) {
        return posts.findPublishedBySlug(slug);
    }

    @GetMapping("/articles")
    public List<Article> articles() {
        return articles.findPublished();
    }

    @GetMapping("/articles/{slug}")
    public Article article(@PathVariable String slug) {
        return articles.findPublishedBySlug(slug);
    }

    @GetMapping("/vlogs")
    public List<Vlog> vlogs() {
        return vlogs.findPublished();
    }

    @GetMapping("/vlogs/{slug}")
    public Vlog vlog(@PathVariable String slug) {
        return vlogs.findPublishedBySlug(slug);
    }

    @GetMapping("/essays")
    public List<Essay> essays() {
        return essays.findPublished();
    }

    @GetMapping("/essays/{slug}")
    public Essay essay(@PathVariable String slug) {
        return essays.findPublishedBySlug(slug);
    }

    @GetMapping("/quotes")
    public List<Quote> quotes() {
        return quotes.findPublished();
    }

    @GetMapping("/experiences")
    public List<Experience> experiences() {
        return experiences.findAll();
    }

    @GetMapping("/education")
    public List<Education> education() {
        return education.findAll();
    }

    @GetMapping("/certifications")
    public List<Certification> certifications() {
        return certifications.findAll();
    }

    @GetMapping("/skills")
    public List<Skill> skills() {
        return skills.findAll();
    }

    @PostMapping("/contact")
    public ResponseEntity<Map<String, String>> contact(@Valid @RequestBody ContactMessage message) {
        messages.receive(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Mensagem recebida. Obrigado pelo contacto."));
    }
}
