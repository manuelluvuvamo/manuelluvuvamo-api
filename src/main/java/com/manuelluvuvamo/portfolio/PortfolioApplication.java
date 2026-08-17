package com.manuelluvuvamo.portfolio;

import com.manuelluvuvamo.portfolio.config.AdminProperties;
import com.manuelluvuvamo.portfolio.config.CorsProperties;
import com.manuelluvuvamo.portfolio.config.JwtProperties;
import com.manuelluvuvamo.portfolio.config.SeedProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties({
        JwtProperties.class,
        AdminProperties.class,
        CorsProperties.class,
        SeedProperties.class
})
public class PortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }
}
