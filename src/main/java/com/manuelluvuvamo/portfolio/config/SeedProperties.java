package com.manuelluvuvamo.portfolio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "portfolio.seed")
public record SeedProperties(boolean enabled) {
}
