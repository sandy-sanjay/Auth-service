package com.ordernexus.authservice.controller;

import com.ordernexus.authservice.dto.LoginRequest;
import com.ordernexus.authservice.dto.LoginResponse;
import com.ordernexus.authservice.entity.AuthUser;
import com.ordernexus.authservice.repository.AuthUserRepository;
import com.ordernexus.authservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // Temporary in-memory user
        if (!request.getUsername().equals("admin") ||
                !request.getPassword().equals("admin123")) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken("admin", "ADMIN");

        return ResponseEntity.ok(new LoginResponse(token, "admin", "ADMIN"));
    }
}
