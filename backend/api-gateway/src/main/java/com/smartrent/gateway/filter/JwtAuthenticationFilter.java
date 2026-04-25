package com.smartrent.gateway.filter;

import com.smartrent.gateway.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    @Value("${app.public-paths}")
    private List<String> publicPaths;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // TODO: check if path is public; if so, chain.filter(exchange)
            // TODO: extract Authorization header
            // TODO: validate JWT via jwtUtil
            // TODO: if invalid, return 401
            // TODO: extract userId, role, email claims and add as X-User-Id, X-User-Role, X-User-Email headers
            // TODO: chain.filter(mutated exchange)
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Empty config class
    }
}
