package com.manuelluvuvamo.portfolio.web.admin;

import com.manuelluvuvamo.portfolio.domain.Certification;
import com.manuelluvuvamo.portfolio.service.CertificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/certifications")
@Tag(name = "Admin · Certificacoes")
@SecurityRequirement(name = "bearer-jwt")
public class CertificationAdminController extends AbstractAdminController<Certification> {

    public CertificationAdminController(CertificationService service) {
        super(service);
    }
}
