package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Experiencia profissional, tal como aparece no CV. */
@Getter
@Setter
@Document("experiences")
public class Experience extends BaseDocument {

    @NotBlank(message = "A empresa e obrigatoria.")
    private String company;

    @NotBlank(message = "O cargo e obrigatorio.")
    private String role;

    private String location;

    private String companyUrl;

    @NotNull(message = "A data de inicio e obrigatoria.")
    private LocalDate startDate;

    /** Vazio quando o cargo ainda esta em curso. */
    private LocalDate endDate;

    private boolean current;

    private String description;

    /** Responsabilidades e resultados, uma por linha. */
    private List<String> highlights = new ArrayList<>();

    /** Projectos tocados neste cargo. */
    private List<String> projects = new ArrayList<>();

    private int orderIndex;
}
