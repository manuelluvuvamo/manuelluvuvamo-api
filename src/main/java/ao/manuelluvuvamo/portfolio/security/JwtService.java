package ao.manuelluvuvamo.portfolio.security;

import ao.manuelluvuvamo.portfolio.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * Emite e valida os tokens do dashboard. O access token e curto e viaja em
 * cada pedido; o refresh token e longo e so serve para pedir um novo access.
 */
@Service
public class JwtService {

    private static final String CLAIM_TYPE = "typ";
    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";

    private final SecretKey key;
    private final JwtProperties properties;

    public JwtService(JwtProperties properties) {
        byte[] secret = properties.secret().getBytes(StandardCharsets.UTF_8);
        if (secret.length < 32) {
            throw new IllegalStateException(
                    "portfolio.jwt.secret precisa de pelo menos 32 bytes para HS256. Define JWT_SECRET.");
        }
        this.key = Keys.hmacShaKeyFor(secret);
        this.properties = properties;
    }

    public String generateAccessToken(String subject, String role) {
        return build(subject, Map.of(CLAIM_TYPE, TYPE_ACCESS, "role", role),
                Duration.ofMinutes(properties.accessTokenMinutes()));
    }

    public String generateRefreshToken(String subject) {
        return build(subject, Map.of(CLAIM_TYPE, TYPE_REFRESH),
                Duration.ofDays(properties.refreshTokenDays()));
    }

    public long accessTokenSeconds() {
        return Duration.ofMinutes(properties.accessTokenMinutes()).toSeconds();
    }

    public long refreshTokenSeconds() {
        return Duration.ofDays(properties.refreshTokenDays()).toSeconds();
    }

    /** Devolve o email do titular, ou null se o token for invalido ou do tipo errado. */
    public String subjectOfAccessToken(String token) {
        return subjectOf(token, TYPE_ACCESS);
    }

    public String subjectOfRefreshToken(String token) {
        return subjectOf(token, TYPE_REFRESH);
    }

    private String subjectOf(String token, String expectedType) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .requireIssuer(properties.issuer())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            if (!expectedType.equals(claims.get(CLAIM_TYPE, String.class))) {
                return null;
            }
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            return null;
        }
    }

    private String build(String subject, Map<String, ?> claims, Duration ttl) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(subject)
                .issuer(properties.issuer())
                .claims(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(ttl)))
                .signWith(key)
                .compact();
    }
}
