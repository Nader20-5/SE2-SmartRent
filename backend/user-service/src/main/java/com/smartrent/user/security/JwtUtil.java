package com.smartrent.user.security;

import com.smartrent.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    public String generateToken(User user) {
        // TODO: implement
        return null;
    }

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
