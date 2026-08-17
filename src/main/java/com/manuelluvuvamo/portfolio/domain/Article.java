package com.manuelluvuvamo.portfolio.domain;

import com.manuelluvuvamo.portfolio.common.BaseDocument;
import com.manuelluvuvamo.portfolio.common.ContentStatus;
import com.manuelluvuvamo.portfolio.common.Sluggable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Artigo publicado para a comunidade (ex.: "Artigo Semanal"). Pode ser
 * conteudo proprio em markdown ou apenas uma ligacao para onde foi publicado.
 */
@Getter
@Setter
@Document("articles")
public class Article extends BaseDocument implements Sluggable {

    @NotBlank(message = "O titulo e obrigatorio.")
    private String title;

    @Indexed(unique = true)
    private String slug;

    private String excerpt;

    /** Conteudo em markdown, quando o artigo vive aqui. */
    private String content;

    /** Ligacao externa, quando o artigo foi publicado noutro sitio. */
    private String externalUrl;

    /** Onde foi publicado, ex.: "Artigo Semanal", "LinkedIn". */
    private String source;

    private List<String> tags = new ArrayList<>();

    private Instant publishedAt;

    private ContentStatus status = ContentStatus.DRAFT;

    @Override
    public String slugSource() {
        return title;
    }
}
