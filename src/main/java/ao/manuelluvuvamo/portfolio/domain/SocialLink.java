package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("social_links")
public class SocialLink extends BaseDocument {

    @NotBlank(message = "O rotulo e obrigatorio.")
    private String label;

    @NotBlank(message = "O endereco e obrigatorio.")
    private String url;

    /** Nome do icone lucide-react usado no frontend, ex.: "github". */
    private String icon;

    private String username;

    private int orderIndex;

    private boolean visible = true;
}
