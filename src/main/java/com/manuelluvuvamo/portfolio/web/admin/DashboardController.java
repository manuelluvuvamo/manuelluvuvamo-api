package com.manuelluvuvamo.portfolio.web.admin;

import com.manuelluvuvamo.portfolio.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/** Contadores mostrados na pagina de entrada do dashboard. */
@RestController
@RequestMapping("/api/v1/admin/dashboard")
@Tag(name = "Admin · Dashboard")
@SecurityRequirement(name = "bearer-jwt")
public class DashboardController {

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

    public DashboardController(ProjectService projects, PostService posts, ArticleService articles,
                               VlogService vlogs, EssayService essays, QuoteService quotes,
                               ExperienceService experiences, EducationService education,
                               CertificationService certifications, SkillService skills,
                               SocialLinkService socialLinks, ContactMessageService messages) {
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

    @GetMapping("/stats")
    public Map<String, Long> stats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        stats.put("projects", projects.count());
        stats.put("posts", posts.count());
        stats.put("articles", articles.count());
        stats.put("vlogs", vlogs.count());
        stats.put("essays", essays.count());
        stats.put("quotes", quotes.count());
        stats.put("experiences", experiences.count());
        stats.put("education", education.count());
        stats.put("certifications", certifications.count());
        stats.put("skills", skills.count());
        stats.put("socialLinks", socialLinks.count());
        stats.put("messages", messages.count());
        stats.put("unreadMessages", messages.unreadCount());
        return stats;
    }
}
