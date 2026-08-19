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

    public enum Kind {
        /** Credencial atribuida por uma entidade apos avaliacao. */
        CERTIFICATION,
        /** Formacao concluida, com certificado de conclusao. */
        COURSE
    }

    /**
     * Documentos antigos nao tem este campo e sao lidos como COURSE, que e o
     * caso mais comum — uma certificacao a serio e a excepcao, nao a regra.
     */
    private Kind kind = Kind.COURSE;

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
