package com.ironhack.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/crm/leads/**")
                        .uri("lb://LEAD-SERVICE"))
                .route(p -> p.path("/crm/contact/**")
                        .uri("lb://CONTACT-SERVICE"))
                .route(p -> p.path("/crm/account/**")
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p.path("/crm/salesrep/**")
                        .uri("lb://SALESREP-SERVICE"))
                .route(p -> p.path("/crm/report/**")
                        .uri("lb://REPORT-SERVICE"))
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
