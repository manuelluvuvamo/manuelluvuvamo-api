package ao.manuelluvuvamo.portfolio;

import ao.manuelluvuvamo.portfolio.config.AdminProperties;
import ao.manuelluvuvamo.portfolio.config.CorsProperties;
import ao.manuelluvuvamo.portfolio.config.JwtProperties;
import ao.manuelluvuvamo.portfolio.config.SeedProperties;
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
