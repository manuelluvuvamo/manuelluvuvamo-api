package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.SocialLink;
import ao.manuelluvuvamo.portfolio.service.SocialLinkService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/social-links")
@Tag(name = "Admin · Redes sociais")
@SecurityRequirement(name = "bearer-jwt")
public class SocialLinkAdminController extends AbstractAdminController<SocialLink> {

    public SocialLinkAdminController(SocialLinkService service) {
        super(service);
    }
}
