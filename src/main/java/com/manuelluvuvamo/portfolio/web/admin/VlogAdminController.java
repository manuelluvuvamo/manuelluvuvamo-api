package com.manuelluvuvamo.portfolio.web.admin;

import com.manuelluvuvamo.portfolio.domain.Vlog;
import com.manuelluvuvamo.portfolio.service.VlogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/vlogs")
@Tag(name = "Admin · Vlogs")
@SecurityRequirement(name = "bearer-jwt")
public class VlogAdminController extends AbstractAdminController<Vlog> {

    public VlogAdminController(VlogService service) {
        super(service);
    }
}
