package com.smartrent.gateway.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    public String extractUserId(String token) {
        // TODO: implement
        return null;
    }

    public String extractRole(String token) {
        // TODO: implement
        return null;
    }

    public String extractEmail(String token) {
        // TODO: implement
        return null;
    }

    public boolean validateToken(String token) {
        // TODO: implement
        return false;
    }
}
