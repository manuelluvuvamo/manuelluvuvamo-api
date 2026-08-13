package ao.manuelluvuvamo.portfolio.web;

import ao.manuelluvuvamo.portfolio.security.AuthService;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.AuthResponse;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.AuthenticatedUser;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.ChangePasswordRequest;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.LoginRequest;
import ao.manuelluvuvamo.portfolio.security.dto.AuthDtos.RefreshRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticacao")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return authService.refresh(request.refreshToken());
    }

    @GetMapping("/me")
    public AuthenticatedUser me(@AuthenticationPrincipal UserDetails principal) {
        return authService.me(principal.getUsername());
    }

    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal UserDetails principal,
                                               @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(principal.getUsername(), request);
        return ResponseEntity.noContent().build();
    }
}
