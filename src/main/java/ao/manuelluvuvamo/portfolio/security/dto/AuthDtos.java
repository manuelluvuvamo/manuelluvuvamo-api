package ao.manuelluvuvamo.portfolio.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Contratos de entrada e saida da autenticacao do dashboard. */
public final class AuthDtos {

    private AuthDtos() {
    }

    public record LoginRequest(
            @NotBlank(message = "O email e obrigatorio.")
            @Email(message = "Email invalido.")
            String email,

            @NotBlank(message = "A password e obrigatoria.")
            String password
    ) {
    }

    public record RefreshRequest(
            @NotBlank(message = "O refresh token e obrigatorio.")
            String refreshToken
    ) {
    }

    public record ChangePasswordRequest(
            @NotBlank(message = "A password actual e obrigatoria.")
            String currentPassword,

            @NotBlank(message = "A nova password e obrigatoria.")
            @Size(min = 8, message = "A nova password precisa de pelo menos 8 caracteres.")
            String newPassword
    ) {
    }

    public record AuthenticatedUser(String id, String name, String email, String role) {
    }

    public record AuthResponse(
            String accessToken,
            String refreshToken,
            long expiresIn,
            long refreshExpiresIn,
            AuthenticatedUser user
    ) {
    }
}
