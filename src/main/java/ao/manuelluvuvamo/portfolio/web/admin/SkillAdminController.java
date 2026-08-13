package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Skill;
import ao.manuelluvuvamo.portfolio.service.SkillService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/skills")
@Tag(name = "Admin · Competencias")
@SecurityRequirement(name = "bearer-jwt")
public class SkillAdminController extends AbstractAdminController<Skill> {

    public SkillAdminController(SkillService service) {
        super(service);
    }
}
