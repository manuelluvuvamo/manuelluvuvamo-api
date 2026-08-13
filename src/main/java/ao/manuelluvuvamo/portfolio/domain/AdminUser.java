package ao.manuelluvuvamo.portfolio.domain;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * Utilizador do dashboard. Nunca e exposto na API publica e a password
 * so viaja como hash BCrypt.
 */
@Getter
@Setter
@Document("admin_users")
public class AdminUser extends BaseDocument {

    @Indexed(unique = true)
    private String email;

    @JsonIgnore
    private String passwordHash;

    private String name;

    private String role = "ADMIN";

    private boolean enabled = true;

    private Instant lastLoginAt;
}
