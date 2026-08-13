package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Post;
import ao.manuelluvuvamo.portfolio.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/posts")
@Tag(name = "Admin · Blog")
@SecurityRequirement(name = "bearer-jwt")
public class PostAdminController extends AbstractAdminController<Post> {

    public PostAdminController(PostService service) {
        super(service);
    }
}
