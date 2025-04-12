package com.lian.marketing.apigateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${gateway.route1.id}")
    private String route1Id;

    @Value("${gateway.route1.uri}")
    private String route1Uri;

    @Value("${gateway.route1.predicates}")
    private String[] route1Paths;

    @Value("${gateway.route2.id}")
    private String route2Id;

    @Value("${gateway.route2.uri}")
    private String route2Uri;

    @Value("${gateway.route2.predicates}")
    private String[] route2Paths;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route1Id, r -> r
                        .path(route1Paths)
                        .uri(route1Uri)
                )
                .route(route2Id, r -> r
                        .path(route2Paths)
                        .uri(route2Uri)
                )
                .build();
    }

}
