package ao.manuelluvuvamo.portfolio.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Campos comuns a todos os documentos: identificador e datas de auditoria.
 * As datas sao preenchidas pelo Mongo auditing e nunca vem do cliente.
 */
@Getter
@Setter
public abstract class BaseDocument {

    @Id
    private String id;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;
}
