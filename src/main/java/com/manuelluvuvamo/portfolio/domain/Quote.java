package com.manuelluvuvamo.portfolio.domain;

import com.manuelluvuvamo.portfolio.common.BaseDocument;
import com.manuelluvuvamo.portfolio.common.ContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/** Frase curta para mostrar no portfolio. */
@Getter
@Setter
@Document("quotes")
public class Quote extends BaseDocument {

    @NotBlank(message = "O texto da frase e obrigatorio.")
    @Size(max = 500, message = "A frase nao deve passar dos 500 caracteres.")
    private String text;

    /** Vazio quando a frase e da autoria do proprio Manuel. */
    private String author;

    /** Contexto ou fonte, ex.: "Sessao de mentoria, 2025". */
    private String context;

    private boolean featured;

    private int orderIndex;

    private ContentStatus status = ContentStatus.PUBLISHED;
}
