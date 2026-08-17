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

/** Video publicado no YouTube, Instagram ou TikTok. */
@Getter
@Setter
@Document("vlogs")
public class Vlog extends BaseDocument implements Sluggable {

    public enum Platform {
        YOUTUBE, INSTAGRAM, TIKTOK, LINKEDIN, OTHER
    }

    @NotBlank(message = "O titulo e obrigatorio.")
    private String title;

    @Indexed(unique = true)
    private String slug;

    private String description;

    @NotBlank(message = "O endereco do video e obrigatorio.")
    private String videoUrl;

    private Platform platform = Platform.YOUTUBE;

    private String thumbnailUrl;

    private Integer durationSeconds;

    private List<String> tags = new ArrayList<>();

    private Instant publishedAt;

    private boolean featured;

    private ContentStatus status = ContentStatus.DRAFT;

    @Override
    public String slugSource() {
        return title;
    }
}
