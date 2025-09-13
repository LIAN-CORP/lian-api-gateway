package com.lian.marketing.apigateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GatewayLoggerConfig {

  @Bean
  public GlobalFilter logFilter(){
    return (exchange, chain) -> {
      log.info("Incoming Request: {} {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());
      return chain.filter(exchange);
    };
  }

}
