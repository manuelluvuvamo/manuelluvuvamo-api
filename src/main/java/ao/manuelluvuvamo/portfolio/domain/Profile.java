package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Documento unico com a identidade do portfolio. Existe sempre exactamente
 * um, identificado pela chave {@link #SINGLETON_KEY}.
 */
@Getter
@Setter
@Document("profile")
public class Profile extends BaseDocument {

    public static final String SINGLETON_KEY = "default";

    @Indexed(unique = true)
    private String key = SINGLETON_KEY;

    @NotBlank(message = "O nome e obrigatorio.")
    private String fullName;

    /** Uma linha, ex.: "Lider tecnico e mentor em Luanda". */
    private String headline;
    private String headlineEn;

    /** Paragrafo de abertura da home. */
    private String shortBio;
    private String shortBioEn;

    /** Texto completo da pagina "sobre", em markdown. */
    private String longBio;
    private String longBioEn;

    private String currentRole;
    private String currentCompany;
    private String currentCompanyUrl;

    @Email(message = "Email invalido.")
    private String email;

    private String phone;
    private String location;
    private String avatarUrl;

    private String resumePtUrl;
    private String resumeEnUrl;

    private boolean availableForWork;

    /** Mensagem opcional mostrada no rodape ou no estado de disponibilidade. */
    private String availabilityNote;
}
