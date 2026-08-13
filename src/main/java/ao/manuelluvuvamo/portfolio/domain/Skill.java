package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("skills")
public class Skill extends BaseDocument {

    @NotBlank(message = "O nome e obrigatorio.")
    private String name;

    /** Agrupador do frontend, ex.: "Backend", "Frontend", "DevOps". */
    @NotBlank(message = "A categoria e obrigatoria.")
    private String category;

    @Min(value = 1, message = "O nivel vai de 1 a 5.")
    @Max(value = 5, message = "O nivel vai de 1 a 5.")
    private int level = 3;

    private boolean featured;

    private int orderIndex;
}
