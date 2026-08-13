package ao.manuelluvuvamo.portfolio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "portfolio.admin")
public record AdminProperties(String email, String password, String name) {
}
