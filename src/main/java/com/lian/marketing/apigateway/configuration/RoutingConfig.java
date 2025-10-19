package com.lian.marketing.apigateway.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.cloud.gateway")
@Getter @Setter
public class RoutingConfig {
  private List<RouteConfig> routes;

  @Getter @Setter
  public static class RouteConfig {
    private String id;
    private String uri;
    private List<String> predicates;
  }
}
