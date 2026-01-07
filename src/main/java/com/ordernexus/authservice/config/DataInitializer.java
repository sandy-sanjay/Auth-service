package com.ordernexus.authservice.config;

import com.ordernexus.authservice.entity.AuthUser;
import com.ordernexus.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createAdmin() {
        return args -> {

            if (repository.findByUsername("admin").isEmpty()) {

                AuthUser admin = new AuthUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");

                repository.save(admin);

                System.out.println("✅ ADMIN CREATED");
            }
        };
    }
}
