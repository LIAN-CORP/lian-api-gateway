package com.lian.marketing.apigateway.configuration.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CorsPreflightFilter implements GlobalFilter, Ordered {


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    if (HttpMethod.OPTIONS.equals(request.getMethod())) {
      exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
      exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
      exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
      exchange.getResponse().getHeaders().add("Access-Control-Max-Age", "3600");
      return exchange.getResponse().setComplete();
    }
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return -1;
  }
}
