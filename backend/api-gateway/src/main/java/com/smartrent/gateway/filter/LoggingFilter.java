package com.smartrent.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO: log incoming method + path
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // TODO: log response status after chain.filter()
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
