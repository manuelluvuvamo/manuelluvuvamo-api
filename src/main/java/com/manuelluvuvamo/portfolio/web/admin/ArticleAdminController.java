package com.manuelluvuvamo.portfolio.web.admin;

import com.manuelluvuvamo.portfolio.domain.Article;
import com.manuelluvuvamo.portfolio.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/articles")
@Tag(name = "Admin · Artigos")
@SecurityRequirement(name = "bearer-jwt")
public class ArticleAdminController extends AbstractAdminController<Article> {

    public ArticleAdminController(ArticleService service) {
        super(service);
    }
}
