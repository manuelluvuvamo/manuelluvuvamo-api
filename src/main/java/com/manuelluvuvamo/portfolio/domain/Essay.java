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

/** Ensaio: texto mais longo e opinativo, separado do blog tecnico. */
@Getter
@Setter
@Document("essays")
public class Essay extends BaseDocument implements Sluggable {

    @NotBlank(message = "O titulo e obrigatorio.")
    private String title;

    @Indexed(unique = true)
    private String slug;

    private String subtitle;

    @NotBlank(message = "O conteudo e obrigatorio.")
    private String content;

    private List<String> tags = new ArrayList<>();

    private Instant publishedAt;

    private ContentStatus status = ContentStatus.DRAFT;

    @Override
    public String slugSource() {
        return title;
    }
}
