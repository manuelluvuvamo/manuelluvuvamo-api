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

/** Artigo do blog, escrito em markdown e servido pela API. */
@Getter
@Setter
@Document("posts")
public class Post extends BaseDocument implements Sluggable {

    @NotBlank(message = "O titulo e obrigatorio.")
    private String title;

    @Indexed(unique = true)
    private String slug;

    @NotBlank(message = "O excerto e obrigatorio.")
    private String excerpt;

    @NotBlank(message = "O conteudo e obrigatorio.")
    private String content;

    private String coverImage;

    private List<String> tags = new ArrayList<>();

    private Integer readingMinutes;

    private Instant publishedAt;

    private boolean featured;

    private ContentStatus status = ContentStatus.DRAFT;

    /** Quantas vezes o artigo foi aberto. */
    private long viewCount;

    /** Quantas vezes foi lido até ao fim. */
    private long readCount;

    @Override
    public String slugSource() {
        return title;
    }
}
