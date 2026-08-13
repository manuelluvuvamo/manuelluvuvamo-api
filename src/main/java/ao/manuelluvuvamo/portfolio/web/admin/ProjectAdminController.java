package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Project;
import ao.manuelluvuvamo.portfolio.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/projects")
@Tag(name = "Admin · Projectos")
@SecurityRequirement(name = "bearer-jwt")
public class ProjectAdminController extends AbstractAdminController<Project> {

    public ProjectAdminController(ProjectService service) {
        super(service);
    }
}
