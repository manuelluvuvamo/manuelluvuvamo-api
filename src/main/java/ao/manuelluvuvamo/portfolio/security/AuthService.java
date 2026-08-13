package ao.manuelluvuvamo.portfolio.security;

import ao.manuelluvuvamo.portfolio.domain.AdminUser;
import ao.manuelluvuvamo.portfolio.error.ApiException;
import ao.manuelluvuvamo.portfolio.repository.AdminUserRepository;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.AuthResponse;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.AuthenticatedUser;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.ChangePasswordRequest;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AdminUserRepository users;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager,
                       AdminUserRepository users,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.users = users;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest request) {
        // Lanca BadCredentialsException, tratada no GlobalExceptionHandler como 401.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        AdminUser user = requireUser(request.email());
        user.setLastLoginAt(Instant.now());
        users.save(user);

        return tokensFor(user);
    }

    public AuthResponse refresh(String refreshToken) {
        String email = jwtService.subjectOfRefreshToken(refreshToken);
        if (email == null) {
            throw ApiException.unauthorized("Refresh token invalido ou expirado.");
        }
        AdminUser user = requireUser(email);
        if (!user.isEnabled()) {
            throw ApiException.unauthorized("Conta desactivada.");
        }
        return tokensFor(user);
    }

    public AuthenticatedUser me(String email) {
        return toAuthenticatedUser(requireUser(email));
    }

    public void changePassword(String email, ChangePasswordRequest request) {
        AdminUser user = requireUser(email);
        if (!passwordEncoder.matches(request.currentPassword(), user.getPasswordHash())) {
            throw ApiException.unauthorized("A password actual nao confere.");
        }
        user.setPasswordHash(passwordEncoder.encode(request.newPassword()));
        users.save(user);
    }

    private AuthResponse tokensFor(AdminUser user) {
        return new AuthResponse(
                jwtService.generateAccessToken(user.getEmail(), user.getRole()),
                jwtService.generateRefreshToken(user.getEmail()),
                jwtService.accessTokenSeconds(),
                jwtService.refreshTokenSeconds(),
                toAuthenticatedUser(user));
    }

    private AdminUser requireUser(String email) {
        return users.findByEmailIgnoreCase(email)
                .orElseThrow(() -> ApiException.unauthorized("Credenciais invalidas."));
    }

    private AuthenticatedUser toAuthenticatedUser(AdminUser user) {
        return new AuthenticatedUser(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
