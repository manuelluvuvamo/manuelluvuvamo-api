package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Essay;
import ao.manuelluvuvamo.portfolio.service.EssayService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/essays")
@Tag(name = "Admin · Ensaios")
@SecurityRequirement(name = "bearer-jwt")
public class EssayAdminController extends AbstractAdminController<Essay> {

    public EssayAdminController(EssayService service) {
        super(service);
    }
}
