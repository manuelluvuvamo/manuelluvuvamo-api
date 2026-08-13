package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Quote;
import ao.manuelluvuvamo.portfolio.service.QuoteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/quotes")
@Tag(name = "Admin · Frases")
@SecurityRequirement(name = "bearer-jwt")
public class QuoteAdminController extends AbstractAdminController<Quote> {

    public QuoteAdminController(QuoteService service) {
        super(service);
    }
}
