package com.ordernexus.authservice.config;

import com.ordernexus.authservice.entity.AuthUser;
import com.ordernexus.authservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AuthUserRepository authUserRepository,
                           PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (authUserRepository.count() == 0) {
            AuthUser admin = new AuthUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            authUserRepository.save(admin);

            System.out.println("✅ Admin user created in DB");
        }
    }
}
