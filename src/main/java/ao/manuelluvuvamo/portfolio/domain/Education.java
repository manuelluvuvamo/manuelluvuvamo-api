package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document("education")
public class Education extends BaseDocument {

    @NotBlank(message = "A instituicao e obrigatoria.")
    private String institution;

    @NotBlank(message = "O grau ou curso e obrigatorio.")
    private String degree;

    private String field;

    private String location;

    private String institutionUrl;

    @NotNull(message = "A data de inicio e obrigatoria.")
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean current;

    private String description;

    /** Classificacao final, ex.: "17 valores". */
    private String grade;

    private int orderIndex;
}
