package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import ao.manuelluvuvamo.portfolio.common.ContentStatus;
import ao.manuelluvuvamo.portfolio.common.Sluggable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document("projects")
public class Project extends BaseDocument implements Sluggable {

    public enum Category {
        /** Trabalho profissional, entregue a um cliente ou empregador. */
        PROFESSIONAL,
        /** Projecto pessoal, prova de conceito ou estudo. */
        EXPERIMENT,
        /** Pacote ou contribuicao open source. */
        OPEN_SOURCE
    }

    @NotBlank(message = "O titulo e obrigatorio.")
    private String title;

    @Indexed(unique = true)
    private String slug;

    @NotBlank(message = "O resumo e obrigatorio.")
    @Size(max = 400, message = "O resumo nao deve passar dos 400 caracteres.")
    private String summary;

    /** Descricao longa em markdown. */
    private String description;

    private String url;
    private String repositoryUrl;
    private String coverImage;

    private List<String> tech = new ArrayList<>();

    /** Papel desempenhado, ex.: "Desenvolvedor backend". */
    private String role;

    private Integer year;

    private Category category = Category.PROFESSIONAL;

    private boolean featured;

    private ContentStatus status = ContentStatus.PUBLISHED;

    private int orderIndex;

    @Override
    public String slugSource() {
        return title;
    }
}
