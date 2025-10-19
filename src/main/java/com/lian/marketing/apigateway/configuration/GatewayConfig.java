package com.lian.marketing.apigateway.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GatewayConfig {

  private final RoutingConfig routingConfig;

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    RouteLocatorBuilder.Builder routes = builder.routes();
    List<RoutingConfig.RouteConfig> routeList = routingConfig.getRoutes();
    if(!routeList.isEmpty()){
      routeList.forEach(route -> {
        String[] paths = route.getPredicates().stream().map(p -> {
          String[] parts = p.split("=", 2);
          if(parts.length == 2 && "Path".equalsIgnoreCase(parts[0].trim())){
            return parts[1].trim();
          } else {
            throw new IllegalArgumentException("Invalid predicate: " + p + " Must be of the form 'Path=/some/path'");
          }
        }).toArray(String[]::new);
        routes.route(route.getId(), r -> r.path(paths).uri(route.getUri()));
      });
    }

    return routes.build();
  }

}
