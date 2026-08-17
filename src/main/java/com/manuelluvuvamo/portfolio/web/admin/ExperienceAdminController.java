package com.manuelluvuvamo.portfolio.web.admin;

import com.manuelluvuvamo.portfolio.domain.Experience;
import com.manuelluvuvamo.portfolio.service.ExperienceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/experiences")
@Tag(name = "Admin · Experiencias")
@SecurityRequirement(name = "bearer-jwt")
public class ExperienceAdminController extends AbstractAdminController<Experience> {

    public ExperienceAdminController(ExperienceService service) {
        super(service);
    }
}
