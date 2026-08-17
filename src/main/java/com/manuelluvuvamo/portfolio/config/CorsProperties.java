package com.manuelluvuvamo.portfolio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "portfolio.cors")
public record CorsProperties(List<String> allowedOrigins) {
}
