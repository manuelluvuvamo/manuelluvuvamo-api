package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Experience;
import ao.manuelluvuvamo.portfolio.service.ExperienceService;
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
