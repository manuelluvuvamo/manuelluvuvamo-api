package com.manuelluvuvamo.portfolio.domain;

import com.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document("certifications")
public class Certification extends BaseDocument {

    @NotBlank(message = "O titulo e obrigatorio.")
    private String title;

    @NotBlank(message = "A entidade emissora e obrigatoria.")
    private String issuer;

    private LocalDate issuedAt;

    private LocalDate expiresAt;

    private String credentialUrl;

    private String description;

    private boolean featured;

    private int orderIndex;
}
