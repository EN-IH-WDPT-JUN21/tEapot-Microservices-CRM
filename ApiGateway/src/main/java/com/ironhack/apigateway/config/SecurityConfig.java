package com.ironhack.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.ArrayList;
import java.util.List;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/crm/**").hasAnyAuthority("ROLE_ADMIN") // reports only for admins?
                .pathMatchers(HttpMethod.GET,"/crm/reports/**").permitAll()
                .pathMatchers(HttpMethod.DELETE, "/crm/**").hasAnyAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.POST,"/crm/leads/**","/crm/account/**","/crm/contact/**",
                        "crm/salesrep/**").hasAnyAuthority("ROLE_SALES_REP", "ROLE_ADMIN")
                .pathMatchers(HttpMethod.PUT,"/crm/leads/**","/crm/account/**","/crm/contact/**",
                        "crm/salesrep/**").hasAnyAuthority("ROLE_SALES_REP", "ROLE_ADMIN")
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails salesrep = User
                .withUsername("salesrep")
                .password(passwordEncoder.encode("salesrep"))
                .roles("SALES_REP")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(salesrep);
        userDetailsList.add(admin);

        return new MapReactiveUserDetailsService(userDetailsList);
    }


}
