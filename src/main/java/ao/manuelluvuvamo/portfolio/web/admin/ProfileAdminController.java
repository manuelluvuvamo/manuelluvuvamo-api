package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.Profile;
import ao.manuelluvuvamo.portfolio.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/profile")
@Tag(name = "Admin · Perfil")
@SecurityRequirement(name = "bearer-jwt")
public class ProfileAdminController {

    private final ProfileService service;

    public ProfileAdminController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public Profile get() {
        return service.get();
    }

    @PutMapping
    public Profile update(@Valid @RequestBody Profile profile) {
        return service.update(profile);
    }
}
