package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/** Mensagem submetida pelo formulario de contacto do portfolio. */
@Getter
@Setter
@Document("contact_messages")
public class ContactMessage extends BaseDocument {

    @NotBlank(message = "O nome e obrigatorio.")
    private String name;

    @NotBlank(message = "O email e obrigatorio.")
    @Email(message = "Email invalido.")
    private String email;

    private String subject;

    @NotBlank(message = "A mensagem e obrigatoria.")
    @Size(max = 5000, message = "A mensagem nao deve passar dos 5000 caracteres.")
    private String message;

    private boolean read;

    private boolean archived;
}
