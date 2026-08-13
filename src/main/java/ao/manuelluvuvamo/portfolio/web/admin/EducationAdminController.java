package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Education;
import ao.manuelluvuvamo.portfolio.service.EducationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/education")
@Tag(name = "Admin · Formacao")
@SecurityRequirement(name = "bearer-jwt")
public class EducationAdminController extends AbstractAdminController<Education> {

    public EducationAdminController(EducationService service) {
        super(service);
    }
}
