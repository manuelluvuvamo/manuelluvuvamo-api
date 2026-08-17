package com.manuelluvuvamo.portfolio.bootstrap;

import com.manuelluvuvamo.portfolio.config.AdminProperties;
import com.manuelluvuvamo.portfolio.domain.AdminUser;
import com.manuelluvuvamo.portfolio.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Cria o utilizador ADMIN no primeiro arranque, a partir de ADMIN_EMAIL e
 * ADMIN_PASSWORD. Se ja existir, nao toca na password — trocar a password
 * faz-se pelo dashboard, nao mexendo nas variaveis de ambiente.
 */
@Component
public class AdminUserSeeder implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminUserSeeder.class);
    private static final String DEFAULT_PASSWORD = "admin12345";

    private final AdminUserRepository users;
    private final AdminProperties properties;
    private final PasswordEncoder passwordEncoder;

    public AdminUserSeeder(AdminUserRepository users, AdminProperties properties, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.properties = properties;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (users.findByEmailIgnoreCase(properties.email()).isPresent()) {
            return;
        }

        AdminUser admin = new AdminUser();
        admin.setEmail(properties.email());
        admin.setName(properties.name());
        admin.setPasswordHash(passwordEncoder.encode(properties.password()));
        admin.setRole("ADMIN");
        admin.setEnabled(true);
        users.save(admin);

        log.info("Utilizador ADMIN criado: {}", properties.email());
        if (DEFAULT_PASSWORD.equals(properties.password())) {
            log.warn("A password de administracao e a de omissao. Define ADMIN_PASSWORD "
                    + "ou troca-a no dashboard antes de publicares.");
        }
    }
}
